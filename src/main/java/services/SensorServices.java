package services;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import commonUtil.TbMpDataUtil;
import data.common.QueryDensity;
import data.common.SensorType;
import data.proj.*;
import data.request.parameters.*;
import data.response.results.GetSensorExtremeDataResult;
import data.response.results.SensorResult;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;
import dataDAO.proj.impl.SensorDataQueryImpl;
import dataDAO.proj.impl.SensorMaxAndMinDataQueryImpl;
import dataDAO.proj.impl.SensorWarnLogCountQueryImpl;
import dataDAO.proj.interfaces.SensorMaxAndMinDataQuery;
import dataDAO.proj.interfaces.SensorNewDataQuery;
import dataDAO.proj.interfaces.SensorWarnLogCountQuery;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/5/4.
 */
public class SensorServices {
    public Integer addSensor(SensorAttriParameter pa) {
        return DataHelper.add(pa.toSensorAttri(), pa.getProjID());
    }

    public void updateSensor(SensorAttriParameter pa) {
        TbSensorAttri sensor = ProjQueryHelper.getSensor(pa.getProjID(), pa.getSensorID());
        pa.setStatus(sensor.getStatus());
        pa.setName(sensor.getName());
        pa.setSensorID(sensor.getId());
        pa.setSensorType(sensor.getSensorType());
        DataHelper.update(pa.toSensorAttri(), pa.getProjID());
    }

    public void deleteSensor(DeleteSensorParameter pa) {
        TbSensorAttri sensor = new TbSensorAttri();
        sensor.setId(pa.getSensorID());
        DataHelper.delete(sensor, pa.getProjID());
    }


    public List<SensorResult> getSensorList(GetSensorListParameter pa) {
        List<SensorResult> result = ProjQueryHelper.getSensorResults(pa.getProjID(), SensorType.valueOf(pa.getSensorType()));
        if (!CollectionUtil.isNullOrEmpty(result)) {
            for (SensorResult sr : result) {
                Object data = sr.getSensorData();
                if (data != null)
                    TbMpDataUtil.changeData(pa.getProjID(), pa.getSensorType(), data);
            }
        }
        return result;
    }

    public SensorResult getSensor(GetSensorParameter pa) {
        SensorResult sr = ProjQueryHelper.getSensorResults(pa.getProjID(), pa.getSensorID());
        if (sr != null) {
            Object data = sr.getSensorData();
            if (data != null) {
                TbSensorAttri attri = sr.getSensorAttri();
                if (attri != null) {
                    TbMpDataUtil.changeData(pa.getProjID(), attri.getSensorType(), data);
                }
            }
        }
        return sr;
    }

    public List<TbSensorAttri> getGroupSensorList(int projID, int groupID) {
        if (groupID == CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return Collections.emptyList();
        Optional<TbGroup> group = ProjQueryHelper.getGroup(projID, groupID).stream().findFirst();
        if (!group.isPresent())
            return Collections.emptyList();
        List<TbSensorAttri> allSensors = ProjQueryHelper.getSensorList(projID, SensorType.All.toInt());
        if (CollectionUtil.isNullOrEmpty(allSensors))
            return Collections.emptyList();
        if (group.get().getSensorType() != SensorType.TYPE_SATURATURE.toInt())
            return allSensors.stream().filter(at -> at.getGroupId() != null &&
                    at.getGroupId() == groupID).collect(Collectors.toList());
            //浸润线 按照扩展属性中的横纵断面查询
        else {
            List<TbSensorAttri> result = new LinkedList<>();
            List<TbSensorAttri> stSensors = allSensors.stream().filter(a -> a.getSensorType() == SensorType.TYPE_SATURATURE.toInt()).collect(Collectors.toList());
            for (TbSensorAttri a : stSensors) {
                if (a.getGroupId() != null && a.getGroupId().intValue() == groupID) {
                    result.add(a);
                    continue;
                }
                if (!StringUtil.isNullOrEmpty(a.getExValues())) {
                    try {
                        StExValues exValues = CommonVariable.getObjectMapper().readValue(a.getExValues(), StExValues.class);
                        if (exValues != null) {
                            if (groupID == exValues.getTransGroupID() || groupID == exValues.getLongGroupID())
                                result.add(a);
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            return result;
        }
    }

    public List<SensorData> getGroupSensorNewData(int projID, int groupID) {
        List<TbGroup> groupList = ProjQueryHelper.getGroup(projID, groupID);
        if (CollectionUtil.isNullOrEmpty(groupList))
            return Collections.emptyList();
        TbGroup group = groupList.get(0);
        List<Integer> attriIDs = getGroupSensorList(projID, groupID).stream()
                .map(attri -> attri.getId()).collect(Collectors.toList());
        SensorNewDataQuery newDataQuery = new SensorDataQueryImpl<>(projID, SensorType.valueOf(group.getSensorType()));
        return newDataQuery.getNewData(attriIDs);
    }


    public List<Object> getSensorDataRecord(GetSensorDataRecordParameter pa) {
        List<Object> result = ProjQueryHelper.getSensorDataRecord(pa);
        if (!CollectionUtil.isNullOrEmpty(result)) {
            for (Object o : result) {
                TbMpDataUtil.changeData(pa.getProjID(), pa.getSensorType(), o);
            }
        }
        return result;
    }

    public List<Object> getSensorDataRecordEx(GetSensorDataRecordExParameter pa) {
        List<Object> result = ProjQueryHelper.getSensorDataRecordEx(pa);
        if (!CollectionUtil.isNullOrEmpty(result)) {
            for (Object o : result) {
                TbMpDataUtil.changeData(pa.getProjID(), pa.getSensorType(), o);
            }
        }
        return result;
    }

    public List<GetSensorExtremeDataResult> getsensorExtremeData(GetSensorExtremeDataParameter pa) {
        SensorMaxAndMinDataQuery dataQuery = new SensorMaxAndMinDataQueryImpl();
        List<SensorData> maxDatas = dataQuery.query(pa.getProjID(), SensorType.valueOf(pa.getSensorType()),
                pa.getSensorIDs(), pa.getStartTime(), pa.getEndTime(),
                QueryDensity.valueOf(pa.getDensity()), true);
        List<SensorData> minDatas = dataQuery.query(pa.getProjID(), SensorType.valueOf(pa.getSensorType()),
                pa.getSensorIDs(), pa.getStartTime(), pa.getEndTime(),
                QueryDensity.valueOf(pa.getDensity()), false);

        SensorWarnLogCountQuery warnLogCountQuery = new SensorWarnLogCountQueryImpl();
        List<ExtremeSensorWarnLogCount> warnLogResults = warnLogCountQuery.getExtremeLogCount(pa.getProjID(), pa.getStartTime(), pa.getEndTime(), pa.getSensorIDs());

        if (maxDatas == null || maxDatas.size() == 0)
            return null;
        List<GetSensorExtremeDataResult> result = new LinkedList<>();
        maxDatas.stream().filter(sd -> sd != null).forEach(sd -> {
            GetSensorExtremeDataResult tempResult = new GetSensorExtremeDataResult();
            tempResult.setMaxData(sd);
            if (minDatas != null) {
                Optional<SensorData> minTempResult = minDatas.stream().filter(sdmin -> sdmin != null).filter(sdmin -> sdmin.getSensorId() == sd.getSensorId()).findFirst();
                if (minTempResult.isPresent())
                    tempResult.setMinData(minTempResult.get());
            }
            if (warnLogResults != null) {
                tempResult.setWarnLogCount(warnLogResults.stream().filter(swc -> swc.getSensorID() == sd.getSensorId()).collect(Collectors.toList()));
            }
            result.add(tempResult);
        });
        if (!CollectionUtil.isNullOrEmpty(result)) {
            result.stream().forEach(ger -> {
                if (ger.getMaxData() != null)
                    TbMpDataUtil.changeData(pa.getProjID(), pa.getSensorType(), ger.getMaxData());
                if (ger.getMinData() != null)
                    TbMpDataUtil.changeData(pa.getProjID(), pa.getSensorType(), ger.getMinData());
            });
        }
        return result;
    }


}

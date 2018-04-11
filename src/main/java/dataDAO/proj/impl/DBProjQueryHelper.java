package dataDAO.proj.impl;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.QueryDensity;
import data.common.SensorType;
import data.common.TbProjInfo;
import data.common.TbRegion;
import data.proj.*;
import data.request.parameters.GetSensorDataRecordParameter;
import data.request.parameters.GetWarnLogListParameter;
import data.response.results.*;
import dataDAO.CommonQueryHelper;
import dataDAO.interfaces.ObjectsQuery;
import dataDAO.proj.interfaces.*;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liudongdong on 2015/6/8.
 */
public class DBProjQueryHelper implements IProjQueryHelper {
    public TbSensorAttri getSensor(int projID, int sid) {
        ObjectsQuery<TbSensorAttri> sQuery = new TbSensorAttriQuery();
        List<TbSensorAttri> results = sQuery.query("ID=" + Integer.toString(sid), projID);
        return results.size() > 0 ? results.get(0) : null;
    }

    public List<TbSensorAttri> getSensorList(int projID, int sensorType) {
        ObjectsQuery<TbSensorAttri> sQuery = new TbSensorAttriQuery();
        List<TbSensorAttri> results = null;
        if (sensorType == SensorType.All.toInt()) {
            results = sQuery.query(null, projID);
        } else {
            results = sQuery.query("SensorType=" + Integer.toString(sensorType), projID);
        }
        if (!CollectionUtil.isNullOrEmpty(results)) {
            results.sort(new Comparator<TbSensorAttri>() {
                @Override
                public int compare(TbSensorAttri o1, TbSensorAttri o2) {
                    if (o1.getAlias() == null && o2.getAlias() == null)
                        return 0;
                    if (o1.getAlias() == null)
                        return -1;
                    if(o2.getAlias()==null)
                        return 1;
                    return o1.getAlias().compareTo(o2.getAlias());
                }
            });
        }
        return results;
    }


    public SensorResult getSensorResults(int projID, int sid) {
        TbSensorAttri attri = getSensor(projID, sid);
        if (attri == null)
            return null;
        SensorNewDataQuery newDataQuery = new SensorDataQueryImpl<>(projID, SensorType.valueOf(attri.getSensorType()));
        NewWarnLogQuery warnLogQuery = new NewWarnLogQueryImpl();
        Object sensorData = newDataQuery.getNewData(sid);
        List<TbWarnLog> logs = warnLogQuery.getLog(projID, Arrays.asList(sid));
        SensorResult sr = new SensorResult(attri, sensorData, null);
        if ((logs != null) && (logs.size() > 0))
            sr.setSensorWarnLog(logs.get(0));
        return sr;
    }

    public SensorResult getSensorResults(int projID, TbSensorAttri attri) {
        int sid = attri.getId();
        SensorNewDataQuery newDataQuery = new SensorDataQueryImpl<>(projID, SensorType.valueOf(attri.getSensorType()));
        NewWarnLogQuery warnLogQuery = new NewWarnLogQueryImpl();
        Object sensorData = newDataQuery.getNewData(sid);
        List<TbWarnLog> logs = warnLogQuery.getLog(projID, Arrays.asList(sid));
        SensorResult sr = new SensorResult(attri, sensorData, null);
        if (logs.size() > 0)
            sr.setSensorWarnLog(logs.get(0));
        return sr;
    }

    public List<SensorResult> getSensorResults(int projID, SensorType sensorType) {
        List<SensorResult> results = new LinkedList<>();
        List<TbSensorAttri> sensors = getSensorList(projID, sensorType.toInt());
        if (sensors.size() == 0)
            return results;
        List<Integer> attriIDs = sensors.stream().map(a -> a.getId()).collect(Collectors.toList());
        NewWarnLogQuery warnLogQuery = new NewWarnLogQueryImpl();
        List<TbWarnLog> warnLogs = warnLogQuery.getLog(projID, attriIDs);
        final List<SensorData> datas = new LinkedList<>();
        SensorNewDataQuery newDataQuery;
        if (sensorType == SensorType.All) {
            Stream.of(SensorType.values()).forEach(s -> {
                //查询相应传感器类型的最新数据
                if (s != SensorType.All) {
                    SensorNewDataQuery sQuery = new SensorDataQueryImpl<>(projID, s);
                    List<Integer> sTypeAttriIDs = sensors.stream().filter(a -> a.getSensorType() == s.toInt())
                            .map(a -> a.getId()).collect(Collectors.toList());
                    datas.addAll(sQuery.getNewData(sTypeAttriIDs));
                }
            });
        } else {
            newDataQuery = new SensorDataQueryImpl<>(projID, sensorType);
            datas.addAll(newDataQuery.getNewData(attriIDs));
        }
        sensors.forEach(attri -> {
            SensorResult sr = new SensorResult();
            sr.setSensorAttri(attri);
            Optional<TbWarnLog> log = warnLogs.stream().filter(w -> w.getSensorId().intValue() == attri.getId()).findFirst();
            if (log.isPresent())
                sr.setSensorWarnLog(log.get());
            Optional<SensorData> attriData = datas.stream().filter(s -> s.getSensorId() == attri.getId()).findFirst();
            if (attriData.isPresent())
                sr.setSensorData(attriData.get());
            results.add(sr);
        });
        return results;
    }


    public List<Object> getSensorDataRecord(GetSensorDataRecordParameter pa) {
        SensorDataQuery sQuery = new SensorDataQueryImpl<>(pa.getProjID(), SensorType.valueOf(pa.getSensorType()));
        return sQuery.getData(pa.getStartTime(), pa.getEndTime(), QueryDensity.valueOf(pa.getDensity()), Arrays.asList(pa.getSensorID()));
    }

    public List<TbWarnValue> getWarnValue(int projID) {
        ObjectsQuery<TbWarnValue> wvQuery = new WarnValueQuery();
        return wvQuery.query(null, projID);
    }

    public TbWarnValue getWarnValue(int projID, int sensorType, String warnType, int warnLevel) {
        ObjectsQuery<TbWarnValue> wvQuery = new WarnValueQuery();
        String where = " SensorType= " + Integer.toString(sensorType) + "  and WarnType='" + warnType + "'"
                + "   and WarnLevel=" + Integer.toString(warnLevel);
        List<TbWarnValue> results = wvQuery.query(where, projID);
        return results.size() > 0 ? results.get(0) : null;
    }

    public List<TbWarnRevise> getWarnRevise(int projID) {
        ObjectsQuery<TbWarnRevise> reQuery = new WarnReviseQuery();
        return reQuery.query(null, projID);
    }

    public TbWarnRevise getWarnRevise(int projID, int sensorType, int sensorID, String warnChildType) {
        ObjectsQuery<TbWarnRevise> reQuery = new WarnReviseQuery();
        String where = "   SensorID=" + Integer.toString(sensorID) + "  and  " +
                "  WarnChildType='" + warnChildType + "'";
        Optional<TbWarnRevise> wv = reQuery.query(where, projID).stream().findFirst();
        return wv.isPresent() ? wv.get() : null;
    }

    public List<WarnReviseResult> getWarnReviseResult(int projID) {
        List<WarnReviseResult> results = new LinkedList<>();
        List<TbWarnRevise> revises = getWarnRevise(projID);
        getSensorList(projID, SensorType.All.toInt()).forEach(attri -> {
            revises.stream().filter(wr -> wr.getSensorId() == attri.getId()).forEach(wr -> {
                WarnReviseResult reResult = new WarnReviseResult();
                reResult.setSensorType(attri.getSensorType());
                reResult.setSensorID(attri.getId());
                reResult.setSensorName(attri.getName());
                reResult.setValue(wr.getValue());
                reResult.setValid(wr.isValid());
                reResult.setWarnChildType(wr.getWarnChildType());
                reResult.setSensorAlias(attri.getAlias());
                results.add(reResult);
            });
        });
        return results;
    }


    public List<WarnLogCountResult> getWarnLogCountList(int projID, Timestamp begin, Timestamp end) {
        List<WarnLogCountResult> results = new LinkedList<>();
        List<TbProjInfo> pis = null;
        if (projID == CommonVariable.DEFAULT_PROJID) {
            pis = CommonQueryHelper.getProjInfo();
        } else {
            pis = Arrays.asList(CommonQueryHelper.getProjInfo(projID));
        }
        pis.forEach(pi -> {
            //查询所有的warnLog
            SensorWarnLogCountQuery warnQuery = new SensorWarnLogCountQueryImpl();
            List<SensorWarnLogCount> warnLogs = warnQuery.getLogCount(pi.getId(), begin, end, null);
            //查询传感器
            getSensorList(pi.getId(), SensorType.All.toInt()).forEach(attri -> {
                //查找相应的sensor是否有warnlogcount信息
                Optional<SensorWarnLogCount> log = warnLogs.stream().filter(swc -> swc.getSensorID() == attri.getId()).findFirst();
                if (log.isPresent()) {
                    WarnLogCountResult wlc = new WarnLogCountResult();
                    wlc.setProjID(pi.getId());
                    wlc.setProjName(pi.getName());
                    wlc.setSensorType(attri.getSensorType());
                    wlc.setSensorID(attri.getId());
                    wlc.setSensorName(attri.getName());
                    wlc.setSensorAlias(attri.getAlias());
                    wlc.setDealedCount(log.get().getDealedCount());
                    wlc.setUndealedCount(log.get().getUndealedCount());
                    results.add(wlc);
                }
            });
        });
        return results;
    }


    public GetWarnLogListResultWarpper getWarnLogList(GetWarnLogListParameter pa) {
        GetWarnLogListResultWarpper result = new GetWarnLogListResultWarpper();
        int count = getWranLogCount(pa.getProjID(), pa.getSensorID(), pa.getStartTime(), pa.getEndTime(), pa.getDealed());
        int totalPage = (int) ((count - 1) / pa.getPageSize()) + 1;
        List<GetWarnLogListResult> logList = new LinkedList<>();
        result.setTotalPage(totalPage);
        result.setPage(pa.getPage());
        result.setWarnLogList(logList);
        TbSensorAttri attri = getSensor(pa.getProjID(), pa.getSensorID());
        ObjectsQuery<TbWarnLog> logQuery = new WarnLogQuery(pa.getPage(), pa.getPageSize());
        StringBuilder where = new StringBuilder();
        where.append(" SensorID=" + Integer.toString(pa.getSensorID()) + "  and  ");
        where.append(" Time between '" + pa.getStartTime() + "' and '" + pa.getEndTime() + "' ");
        if (pa.getDealed() != WarnLogStatus.ALL) {
            where.append(" and Dealed=" + Integer.toString(pa.getDealed()));
        }
        where.append(" order by Time desc  ");
        logQuery.query(where.toString(), pa.getProjID()).forEach(wl -> {
            GetWarnLogListResult wlResult = new GetWarnLogListResult(wl);
            wlResult.setSensorName(attri.getName());
            wlResult.setSensorAlias(attri.getAlias());
            logList.add(wlResult);
        });
        return result;
    }

    public int getWranLogCount(int projID, int sensorID, Timestamp begin, Timestamp end, int warnLogStatus) {
        UniqueSqlQuery<Integer> uq = new UniqueSqlQueryImpl<Integer>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select count(1)  as LogCount from tb_warn_log where ");
        if (sensorID != (CommonVariable.DEFAULT_AUTO_INCREMENT_ID.intValue())) {
            sqlBuilder.append(" SensorID=" + Integer.toString(sensorID) + "  and  ");
        }
        sqlBuilder.append("  `Time` between '" + begin.toString() + "' and '" + end.toString() + "'  ");
        if (warnLogStatus != WarnLogStatus.ALL) {
            sqlBuilder.append("  and Dealed=" + Integer.toString(warnLogStatus));
        }
        Map<String, Type> map = new HashMap<String, Type>();
        map.put("LogCount", StandardBasicTypes.INTEGER);
        return uq.query(projID, sqlBuilder.toString(), map);
    }

    public List<TbWarnPerson> getWarnPersonList(int projID) {
        ObjectsQuery<TbWarnPerson> personQuery = new WarnPersonQuery();
        return personQuery.query(null, projID);
    }

    public TbWarnPerson getWarnPerson(int projID, String userName) {
        ObjectsQuery<TbWarnPerson> personQuery = new WarnPersonQuery();
        String where = " UserName='" + userName + "' ";
        Optional<TbWarnPerson> person = personQuery.query(where, projID).stream().findFirst();
        return person.isPresent() ? person.get() : null;
    }

    /**
     * @param projID
     * @param areaID 等于-1时查询所有
     * @return
     */
    public List<TbMonitorArea> getMonitorArea(int projID, int areaID) {
        ObjectsQuery<TbMonitorArea> mQuery = new MonitorAreaQuery();
        if (areaID == -1)
            return mQuery.query(null, projID);
        else
            return mQuery.query(" ID=" + Integer.toString(areaID), projID);
    }

    public List<TbGroup> getGroup(int projID, int groupID) {
        ObjectsQuery<TbGroup> gQuery = new GroupQuery();
        if (groupID == -1)
            return gQuery.query(null, projID);
        else
            return gQuery.query(" ID=" + Integer.toString(groupID), projID);
    }

    public List<TbMonitorType> getMonitorType(int projID) {
        ObjectsQuery<TbMonitorType> mQuery = new MonitorTypeQuery();
        return mQuery.query(null, projID);
    }

    public List<TbRegion> getRegion(int projID, int regionID) {
        throw new RuntimeException("DBProjQuery没有实现getRegion方法");
    }

    @Override
    public List<TbChannel> getChannels(int projID) {
        ObjectsQuery<TbChannel> cq = new TbChannelQuery();
        return cq.query(null, projID);
    }

    @Override
    public List<TbImageMeta> getImages(int projID) {
        ObjectsQuery<TbImageMeta> cq = new TbImageMetaQuery();
        List<TbImageMeta> images = cq.query(null, projID);
        if (CollectionUtil.isNullOrEmpty(images))
            return images;
        Collections.sort(images, (i1, i2) -> i2.getUploadTime().compareTo(i1.getUploadTime()));
        return images;
    }
}

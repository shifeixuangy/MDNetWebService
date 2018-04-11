package dataDAO;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.QueryDensity;
import data.common.SensorType;
import data.common.TbProjInfo;
import data.common.TbRegion;
import data.proj.*;
import data.request.parameters.GetSensorDataRecordExParameter;
import data.request.parameters.GetSensorDataRecordParameter;
import data.request.parameters.GetWarnLogListParameter;
import data.response.results.*;
import dataDAO.cache.CommonCache;
import dataDAO.cache.ProjCache;
import dataDAO.interfaces.ObjectsQuery;
import dataDAO.proj.impl.*;
import dataDAO.proj.interfaces.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liudongdong on 2015/5/4.
 */
public class ProjQueryHelper {
    private static Log logger = LogFactory.getLog(ProjQueryHelper.class);
    private static CommonCache commonCache = CommonCache.getCommonCache();

    public static TbSensorAttri getSensor(int projID, int sid) {
        Optional<TbSensorAttri> sensor = ProjCache.getProjCache(projID).getSensors().stream().filter(s -> s.getId() == sid).findFirst();
        return sensor.isPresent() ? sensor.get() : null;
    }

    public static List<TbSensorAttri> getSensorList(int projID, int sensorType) {
        List<TbSensorAttri> sensors = ProjCache.getProjCache(projID).getSensors();
        if(sensors==null)
            return new LinkedList<TbSensorAttri>();
        if (sensorType == SensorType.All.toInt())
            return sensors;
        return sensors.stream().filter(s -> s.getSensorType() == sensorType).collect(Collectors.toList());
    }


    public static SensorResult getSensorResults(int projID, int sid) {
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

    public static SensorResult getSensorResults(int projID, TbSensorAttri attri) {
        if (attri == null)
            return null;
        return getSensorResults(projID, attri.getId());
    }

    public static List<SensorResult> getSensorResults(int projID, SensorType sensorType) {
        List<SensorResult> results = new LinkedList<>();
        List<SensorType>validSensorTypes=getValidSensorTypes(projID);
        List<TbSensorAttri> sensors = getSensorList(projID, sensorType.toInt()).stream()
                .filter(a -> (a != null)&&(validSensorTypes.contains(SensorType.valueOf(a.getSensorType()))))
                .collect(Collectors.toList());
        if (CollectionUtil.isNullOrEmpty(sensors))
            return results;
        List<Integer> attriIDs = sensors.stream().map(a -> a.getId()).collect(Collectors.toList());
        NewWarnLogQuery warnLogQuery = new NewWarnLogQueryImpl();
        List<TbWarnLog> warnLogs = warnLogQuery.getLog(projID, attriIDs);
        final List<SensorData> datas = new LinkedList<SensorData>();
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
//        long nullDataCount = datas.stream().filter(sd -> sd == null).count();
//        if (nullDataCount > 0)
//            logger.info("查询的SensorData数据集中有NULL");
        sensors.forEach(attri -> {
            SensorResult sr = new SensorResult();
            sr.setSensorAttri(attri);
            Optional<TbWarnLog> log = warnLogs.stream().filter(w -> w.getSensorId().intValue() == attri.getId()).findFirst();
            if (log.isPresent())
                sr.setSensorWarnLog(log.get());
            Optional<SensorData> attriData = datas.stream().filter(s -> s != null).filter(s -> s.getSensorId() == attri.getId()).findFirst();
            if (attriData.isPresent())
                sr.setSensorData(attriData.get());
            results.add(sr);
        });
        return results;
    }


    public static List<Object> getSensorDataRecord(GetSensorDataRecordParameter pa) {
        SensorDataQuery sQuery = new SensorDataQueryImpl<>(pa.getProjID(), SensorType.valueOf(pa.getSensorType()));
        return sQuery.getData(pa.getStartTime(), pa.getEndTime(), QueryDensity.valueOf(pa.getDensity()), Arrays.asList(pa.getSensorID()));
    }

    public static List<Object>getSensorDataRecordEx(GetSensorDataRecordExParameter pa)
    {
        SensorDataQuery sQuery = new SensorDataQueryImpl<>(pa.getProjID(), SensorType.valueOf(pa.getSensorType()));
        return sQuery.getData(pa.getStartTime(), pa.getEndTime(), QueryDensity.valueOf(pa.getDensity()), pa.getSensorIDs());
    }

    public static List<TbWarnValue> getWarnValue(int projID) {
        ObjectsQuery<TbWarnValue> wvQuery = new WarnValueQuery();
        return wvQuery.query(null, projID);
    }

    public static TbWarnValue getWarnValue(int projID, int sensorType, String warnType, int warnLevel) {
        ObjectsQuery<TbWarnValue> wvQuery = new WarnValueQuery();
        String where = " SensorType= " + Integer.toString(sensorType) + "  and WarnType='" + warnType + "'"
                + "   and WarnLevel=" + Integer.toString(warnLevel);
        List<TbWarnValue> results = wvQuery.query(where, projID);
        return results.size() > 0 ? results.get(0) : null;
    }

    public static List<TbWarnRevise> getWarnRevise(int projID) {
        ObjectsQuery<TbWarnRevise> reQuery = new WarnReviseQuery();
        return reQuery.query(null, projID);
    }

    public static TbWarnRevise getWarnRevise(int projID, int sensorType, int sensorID, String warnChildType) {
        ObjectsQuery<TbWarnRevise> reQuery = new WarnReviseQuery();
        String where = "   SensorID=" + Integer.toString(sensorID) + "  and  " +
                "  WarnChildType='" + warnChildType + "'";
        Optional<TbWarnRevise> wv = reQuery.query(where, projID).stream().findFirst();
        return wv.isPresent() ? wv.get() : null;
    }

    public static List<WarnReviseResult> getWarnReviseResult(int projID) {
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


    public static List<WarnLogCountResult> getWarnLogCountList(int projID, Timestamp begin, Timestamp end) {
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
                WarnLogCountResult wlc = new WarnLogCountResult();
                wlc.setProjID(pi.getId());
                wlc.setProjName(pi.getName());
                wlc.setSensorType(attri.getSensorType());
                wlc.setSensorID(attri.getId());
                wlc.setSensorName(attri.getName());
                wlc.setSensorAlias(attri.getAlias());
                if (log.isPresent()) {
                    wlc.setDealedCount(log.get().getDealedCount());
                    wlc.setUndealedCount(log.get().getUndealedCount());
                }
                //在警报日志中没有查到相应的传感器记录
                else {
                    wlc.setDealedCount(0);
                    wlc.setUndealedCount(0);
                }
                results.add(wlc);
            });
        });
        return results;
    }


    public static GetWarnLogListResultWarpper getWarnLogList(GetWarnLogListParameter pa) {
        GetWarnLogListResultWarpper result = new GetWarnLogListResultWarpper();
        List<TbSensorAttri> sensorAttris = new LinkedList<>();
        //要查某个具体的传感器
        if (pa.getSensorID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            TbSensorAttri attri = getSensor(pa.getProjID(), pa.getSensorID());
            if (attri != null)
                sensorAttris.add(attri);
        } else {
            sensorAttris = getSensorList(pa.getProjID(), pa.getSensorType());
        }
        int count = getWarnLogCount(pa.getProjID(), sensorAttris.stream().map(a -> a.getId()).collect(Collectors.toList()),
                pa.getStartTime(), pa.getEndTime(), pa.getDealed(),pa.getWarnType());
        int totalPage = 0;
        if (count > 0)
            totalPage = (int) ((count - 1) / pa.getPageSize()) + 1;
        List<GetWarnLogListResult> logList = new LinkedList<GetWarnLogListResult>();
        result.setTotalPage(totalPage);
        result.setPage(pa.getPage());
        result.setWarnLogList(logList);
//        TbSensorAttri attri = getSensor(pa.getProjID(), pa.getSensorID());
        ObjectsQuery<TbWarnLog> logQuery = new WarnLogQuery(pa.getPage(), pa.getPageSize());
        StringBuilder where = new StringBuilder();
        if (sensorAttris.size() > 0) {
            where.append(" SensorID in (");
            sensorAttris.stream().map(a -> a.getId()).collect(Collectors.toList()).forEach(i -> {
                where.append(i.toString() + ",");
            });
            where.deleteCharAt(where.lastIndexOf(","));
            where.append(")  and  ");
        }
        where.append(" Time between '" + pa.getStartTime() + "' and '" + pa.getEndTime() + "' ");
        if (pa.getDealed() != WarnLogStatus.ALL) {
            where.append(" and Dealed=" + Integer.toString(pa.getDealed()));
        }
        int warnType=pa.getWarnType();
        if(warnType!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
        {
            if(warnType==0)
                where.append(" and WarnMainType='设备异常' ");
            else
                where.append(" and WarnMainType<>'设备异常' ");
        }
        where.append(" order by Time desc  ");
        final List<TbSensorAttri> fSensorAttris = sensorAttris;
        List<TbWarnLog> warnLogs = logQuery.query(where.toString(), pa.getProjID());
        warnLogs.forEach(wl -> {
            GetWarnLogListResult wlResult = new GetWarnLogListResult(wl);
            Optional<TbSensorAttri> attriTemp = fSensorAttris.stream().filter(t -> t.getId() == wl.getSensorId().intValue()).findFirst();
            if (attriTemp.isPresent()) {
                wlResult.setSensorName(attriTemp.get().getName());
                wlResult.setSensorAlias(attriTemp.get().getAlias());
            }
            logList.add(wlResult);
        });
        return result;
    }

    public static int getWranLogCount(int projID, int sensorID, Timestamp begin, Timestamp end, int warnLogStatus,int warnType) {
//        UniqueSqlQuery<Integer> uq = new UniqueSqlQueryImpl<Integer>();
//        StringBuilder sqlBuilder = new StringBuilder();
//        sqlBuilder.append("select count(1)  as LogCount from tb_warn_log where ");
//        if (sensorID != (CommonVariable.DEFAULT_AUTO_INCREMENT_ID.intValue())) {
//            sqlBuilder.append(" SensorID=" + Integer.toString(sensorID) + "  and  ");
//        }
//        sqlBuilder.append("  `Time` between '" + begin.toString() + "' and '" + end.toString() + "'  ");
//        if (warnLogStatus != WarnLogStatus.ALL) {
//            sqlBuilder.append("  and Dealed=" + Integer.toString(warnLogStatus));
//        }
//        Map<String, Type> map = new HashMap<String, Type>();
//        map.put("LogCount", StandardBasicTypes.INTEGER);
//        return uq.query(projID, sqlBuilder.toString(), map);
        List<Integer>sids=null;
        if(sensorID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            sids=Arrays.asList(sensorID);
        return getWarnLogCount(projID,sids,begin,end,warnLogStatus,warnType);
    }

    public static int getWarnLogCount(int projID, List<Integer> sensorIDs, Timestamp begin, Timestamp end, int warnLogStatus,int warnType) {
        UniqueSqlQuery<Integer> uq = new UniqueSqlQueryImpl<Integer>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select count(1)  as LogCount from tb_warn_log where ");
        if ((sensorIDs != null) && (sensorIDs.size() > 0)) {
            sqlBuilder.append("SensorID in (");
            sensorIDs.forEach(i -> {
                sqlBuilder.append(i.toString() + ",");
            });
            sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
            sqlBuilder.append(")  and  ");
        }
        sqlBuilder.append("  `Time` between '" + begin.toString() + "' and '" + end.toString() + "'  ");
        if (warnLogStatus != WarnLogStatus.ALL) {
            sqlBuilder.append("  and Dealed=" + Integer.toString(warnLogStatus));
        }
       if(warnType!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
       {
           if(warnType==0)
               sqlBuilder.append(" and WarnMainType='设备异常' ");
           else
               sqlBuilder.append(" and WarnMainType<>'设备异常' ");
       }
        Map<String, Type> map = new HashMap<String, Type>();
        map.put("LogCount", StandardBasicTypes.INTEGER);
        return uq.query(projID, sqlBuilder.toString(), map);
    }

    public static boolean hasWarnLog(List<Integer> projIDs, Timestamp begin, Timestamp end) {
        int allCount = 0;
        for (Integer i : projIDs) {
            allCount += getWarnLogCount(i, null, begin, end, WarnLogStatus.UNDEALED,-1);
        }
        return allCount > 0 ? true : false;
    }

    public static TbWarnLog getWarnLog(int projID, int warnLogID) {
        ObjectsQuery<TbWarnLog> logQuery = new WarnLogQuery();
        String where = " ID=" + Integer.toString(warnLogID);
        List<TbWarnLog> logs = logQuery.query(where, projID);
        return logs.size() > 0 ? logs.get(0) : null;
    }

    public static List<TbWarnPerson> getWarnPersonList(int projID) {
        ObjectsQuery<TbWarnPerson> personQuery = new WarnPersonQuery();
        return personQuery.query(null, projID);
    }

    public static TbWarnPerson getWarnPerson(int projID, String userName) {
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
    public static List<TbMonitorArea> getMonitorArea(int projID, int areaID) {
        ObjectsQuery<TbMonitorArea> mQuery = new MonitorAreaQuery();
        if (areaID == -1)
            return mQuery.query(null, projID);
        else
            return mQuery.query(" ID=" + Integer.toString(areaID), projID);
    }

    public static List<TbGroup> getGroup(int projID, int groupID) {
        List<TbGroup>result=ProjCache.getProjCache(projID).getGroups();
        if(!CollectionUtil.isNullOrEmpty(result))
        {
            if(groupID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
                result=result.stream().filter(g->g.getId()==groupID).collect(Collectors.toList());
            result.sort((g1,g2)->{
                if (g1.getAlias() == null && g2.getAlias() == null)
                    return 0;
                if (g1.getAlias() == null)
                    return -1;
                if(g2.getAlias()==null)
                    return 1;
                return g1.getAlias().compareTo(g2.getAlias());
            });
            List<SensorType>validSensorTypes=getValidSensorTypes(projID);
            result=result.stream().filter(g->validSensorTypes.contains(SensorType.valueOf(g.getSensorType()))).collect(Collectors.toList());
        }
        return result;
    }

    public static List<TbMonitorType> getMonitorType(int projID) {
        return ProjCache.getProjCache(projID).getMonitorTypes();
    }
    public static List<TbMonitorType>getMonitorType(int projID,Boolean isValid)
    {
        List<TbMonitorType>monitorTypes=getMonitorType(projID);
        if(isValid==null)
            return monitorTypes;
        if(monitorTypes==null)
            return Collections.emptyList();
        return monitorTypes.stream().filter(mt->isValid.equals(mt.isValid())).collect(Collectors.toList());
    }

    public static List<TbMonitorType>getValidMonitorType(int projID)
    {
        List<TbMonitorType>monitorTypes=getMonitorType(projID);
        if(!CollectionUtil.isNullOrEmpty(monitorTypes))
        {
            monitorTypes=monitorTypes.stream().filter(mt->mt.isValid()).collect(Collectors.toList());
        }
        return monitorTypes;
    }

    public static List<SensorType> getValidSensorTypes(int projID) {
        List<SensorType> result = new LinkedList<>();
        List<TbMonitorType> monitorTypes = getValidMonitorType(projID);
        if (CollectionUtil.isNullOrEmpty(monitorTypes))
            return result;
        result=monitorTypes.stream().map(mt->SensorType.valueOf(mt.getSensorType())).collect(Collectors.toList());
        return result;
    }

    public static List<TbRegion> getRegion(int projID, int regionID) {
        if (regionID == -1)
            return commonCache.getRegions();
        else
            return commonCache.getRegions().stream().filter(r -> r.getId() == regionID).collect(Collectors.toList());
    }

    public static List<TbSensorWarnValue>getSensorWarnValueList(int projID,int sensorID)
    {
        String where=null;
        if(sensorID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
        {
            where=" SensorID="+Integer.toString(sensorID);
        }
        ObjectsQuery<TbSensorWarnValue>wvQuery=new TbSensorWarnValueQuery();
        return wvQuery.query(where,projID);
    }

}
















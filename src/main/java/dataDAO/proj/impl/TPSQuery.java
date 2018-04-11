package dataDAO.proj.impl;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.TimeUtil;
import data.common.QueryDensity;
import data.common.SensorType;
import data.proj.TbSensorAttri;
import data.proj.TbTpsData;
import data.request.parameters.GetSensorDataRecordParameter;
import dataDAO.ExecuteSql;
import dataDAO.ProjQueryHelper;
import dataDAO.proj.interfaces.NativeSqlQuery;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/12/16.
 */
public class TPSQuery {
    public List<TbTpsData> getMinTimeData(int projID,int sensorID)
    {
        List<Integer>sids=new LinkedList<>();
        if(sensorID!= CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            sids.add(sensorID);
        else
        {
            List<TbSensorAttri>tpss= ProjQueryHelper.getSensorList(projID, SensorType.TYPE_TPS.toInt());
            if(CollectionUtil.isNullOrEmpty(tpss))
                return Collections.emptyList();
            sids= tpss.stream().map(t->t.getId()).collect(Collectors.toList());
        }
        StringBuilder sqlBuilder=new StringBuilder();
        sids.forEach(sid->{
            sqlBuilder.append(" select * from tb_tps_data where SensorID="+sid.toString()+" and `Time`= ");
            sqlBuilder.append(" (select min(`Time`) from tb_tps_data where SensorID="+sid.toString()+") ");
            sqlBuilder.append("union");
        });
        sqlBuilder.delete(sqlBuilder.lastIndexOf("union"),sqlBuilder.length());
        NativeSqlQuery<TbTpsData>tpsQuery=new NativeSqlQueryImpl<>();
        return tpsQuery.query(projID,sqlBuilder.toString(),TbTpsData.class);
    }

    public TbTpsData getPreData(int projID, int sensorID, Timestamp time)
    {
        String sid=Integer.toString(sensorID);
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append(" select * from tb_tps_data where SensorID="+sid+" and ");
        sqlBuilder.append(" `Time`=(select max(`Time`) from tb_tps_data where SensorID="+sid+" and `Time`<'"+
                TimeUtil.format(time)+"')");
        NativeSqlQuery<TbTpsData>tq=new NativeSqlQueryImpl<>();
        List<TbTpsData>tpsDatas=tq.query(projID,sqlBuilder.toString(),TbTpsData.class);
        if(CollectionUtil.isNullOrEmpty(tpsDatas))
            return null;
        return tpsDatas.get(0);
    }

    public List<TbTpsData>getTpsData(int projID,int sensorID,Timestamp begin,Timestamp end)
    {
        GetSensorDataRecordParameter pa=new GetSensorDataRecordParameter();
        pa.setProjID(projID);
        pa.setDensity(QueryDensity.Normal.toInt());
        pa.setSensorID(sensorID);
        pa.setSensorType(SensorType.TYPE_TPS.toInt());
        pa.setStartTime(begin);
        pa.setEndTime(end);
        List<Object>tpsObjects=ProjQueryHelper.getSensorDataRecord(pa);
        if(CollectionUtil.isNullOrEmpty(tpsObjects))
            return Collections.emptyList();
        List<TbTpsData>result=new LinkedList<>();
        tpsObjects.forEach(o->{
            result.add((TbTpsData)o);
        });
        return  result;
    }
}

package dataDAO.proj.impl;

import commonUtil.CommonVariable;
import commonUtil.SensorDataSelects;
import data.common.QueryDensity;
import data.common.SensorType;
import data.proj.SensorData;
import dataDAO.proj.interfaces.NativeSqlQuery;
import dataDAO.proj.interfaces.SensorMaxAndMinDataQuery;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/8/15.
 */
public class SensorMaxAndMinDataQueryImpl implements SensorMaxAndMinDataQuery {
    private NativeSqlQuery<SensorData> nativeSqlQuery = new NativeSqlQueryImpl<>();

    @Override
    public List<SensorData> query(int projID, SensorType sType, List<Integer> sensorIDs, Timestamp begin, Timestamp end, QueryDensity density, boolean isMax) {
        //雨量特殊处理
        if((sType==SensorType.TYPE_RAIN)
                &&(density!=QueryDensity.Normal)
                &&(density!=QueryDensity.Day))
            return queryRgMaxAndMin(projID, sensorIDs, begin, end, density, isMax);

        String select = SensorDataSelects.getSensorMaxAndMinSelect(sType, isMax);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(select);
        sqlBuilder.append(" from  " + getTable(density, sType));
        sqlBuilder.append(" where `Time` between '" + begin.toString() + "' and '" + end.toString() + "'  ");
        sqlBuilder.append("  and SensorID in (");
        sensorIDs.forEach(id -> {
            sqlBuilder.append(id.toString() + ",");
        });
        sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
        sqlBuilder.append(")");
        sqlBuilder.append(" group by SensorID ");
        return nativeSqlQuery.query(projID, sqlBuilder.toString(), SensorDataSelects.getExtremeSensorClass(sType));
    }


    private String getTable(QueryDensity density, SensorType sType) {
        String s = SensorDataSelects.getSensorTable(sType);
        if ((density != QueryDensity.Normal) && (density != QueryDensity.Hour))
            s += "_avg";
        return s;
    }

    //    select ID,SensorID,min(`Time`) as `Time`,max(Rainfall) as Rainfall  from
//    (select ID,SensorID,min(`Time`) as `Time`,sum(Rainfall) as Rainfall
//    from tb_rg_data where Time between '2015-11-06 22:18:51' and '2015-11-08 22:18:51'
//    and SensorID in (74,75)
//    group by SensorID,timestampdiff(hour,`Time`,'2016-05-03 10:00:00') ) as rg_temp_table group by SensorID;
    private List<SensorData> queryRgMaxAndMin(int projID, List<Integer> sensorIDs, Timestamp begin, Timestamp end, QueryDensity density, boolean isMax) {
        StringBuilder sqlBuilder=new StringBuilder();
        StringBuilder sensorIDStr=new StringBuilder();
        sensorIDs.forEach(id->{
            sensorIDStr.append(id.toString()+",");
        });
        sensorIDStr.deleteCharAt(sensorIDStr.lastIndexOf(","));
        String tableName=getTable(density,SensorType.TYPE_RAIN);
        sqlBuilder.append(" select ID,SensorID,min(`Time`) as `Time`,max(Rainfall) as Rainfall  from   ");
        sqlBuilder.append(" (select ID,SensorID,min(`Time`) as `Time`,sum(Rainfall) as Rainfall  ");
        sqlBuilder.append(" from "+tableName+" where Time between '"+begin.toString()+"' and '"+end.toString()+"' ");
        sqlBuilder.append(" and SensorID in ("+sensorIDStr+")  ");
        sqlBuilder.append(" group by SensorID,timestampdiff("+density.getGroupTimeField()+",`Time`,'"+ CommonVariable.BASE_DATETIME+"') ) as rg_temp_table group by SensorID; ");
        String sql=sqlBuilder.toString();
        if(!isMax)
            sql=sql.replace("max(Rainfall)","min(Rainfall)");
        return nativeSqlQuery.query(projID, sql, SensorDataSelects.getSensorClass(SensorType.TYPE_RAIN));
    }


}

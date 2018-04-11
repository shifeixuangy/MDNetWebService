package dataDAO.proj.impl;

import commonUtil.CollectionUtil;
import data.proj.ExtremeSensorWarnLogCount;
import data.proj.SensorWarnLogCount;
import dataDAO.proj.interfaces.NativeSqlQuery;
import dataDAO.proj.interfaces.SensorWarnLogCountQuery;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/6.
 */
public class SensorWarnLogCountQueryImpl implements SensorWarnLogCountQuery {
    @Override
    public List<SensorWarnLogCount> getLogCount(int projID, Timestamp begin, Timestamp end, List<Integer>sensorIDs) {
        NativeSqlQuery<SensorWarnLogCount>sQuery=new NativeSqlQueryImpl<>();
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append("select SensorID,sum(case dealed when 1 then 1 else 0 end) as DealedCount,");
        sqlBuilder.append("sum(case dealed when 0 then 1 else 0 end) as UndealedCount from tb_warn_log  ");
        sqlBuilder.append("  where `Time` between '"+begin.toString()+"' and '"+end.toString()+"'  ");
        if((sensorIDs!=null)&&(sensorIDs.size()>0))
        {
            sqlBuilder.append("  and SensorID in  (");
            sensorIDs.forEach(id->{
                sqlBuilder.append(id.toString()+",");
            });
            sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
            sqlBuilder.append(")  ");
        }
        sqlBuilder.append("  group by SensorID");
        return sQuery.query(projID,sqlBuilder.toString(),SensorWarnLogCount.class);
    }

    @Override
    public List<ExtremeSensorWarnLogCount> getExtremeLogCount(int projID, Timestamp begin, Timestamp end, List<Integer>sensorIDs) {
//        select SensorID, count(1) as UndealedCount,WarnChildType from tb_warn_log
//        where `Time` between '2015-10-23 10:32:26' and '2016-10-23 10:32:26' and SensorID in (74,75)
//        and Dealed=0
//        group by SensorID,WarnChildType;
        NativeSqlQuery<ExtremeSensorWarnLogCount>sQuery=new NativeSqlQueryImpl<>();
        StringBuilder sqlBuilder=new StringBuilder();

        sqlBuilder.append(" select SensorID, count(1) as UndealedCount,WarnChildType from tb_warn_log ");
        sqlBuilder.append(" where `Time` between '"+begin.toString()+"' and '"+end.toString()+"' ");
        sqlBuilder.append(" and Dealed=0 ");
        if(!CollectionUtil.isNullOrEmpty(sensorIDs))
        {
            StringBuilder sensorIDsBuilder=new StringBuilder();
            sensorIDs.forEach(i->{
                sensorIDsBuilder.append(Integer.toString(i)+",");
            });
            String sensorIDsStr=sensorIDsBuilder.deleteCharAt(sensorIDsBuilder.lastIndexOf(",")).toString();
            sqlBuilder.append(" and SensorID in("+sensorIDsStr+") ");
        }
        sqlBuilder.append(" group by SensorID,WarnChildType ");
        return sQuery.query(projID,sqlBuilder.toString(),ExtremeSensorWarnLogCount.class);
    }




}

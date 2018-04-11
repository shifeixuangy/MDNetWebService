package dataDAO.proj.impl;

import data.proj.TbWarnLog;
import dataDAO.proj.interfaces.NativeSqlQuery;
import dataDAO.proj.interfaces.NewWarnLogQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/5.
 */
public class NewWarnLogQueryImpl implements NewWarnLogQuery {
    private NativeSqlQuery<TbWarnLog>sqlQuery=new NativeSqlQueryImpl<>();

    @Override
    public List<TbWarnLog> getLog(int projID,List<Integer> sids) {
        if(sids.size()<=0)
            return null;
        StringBuilder sqlBuilder=new StringBuilder();
        sids.forEach(i->{
            sqlBuilder.append(" select * from tb_warn_log where SensorID="+i.toString()+" and Dealed=0 ");
            sqlBuilder.append(" and `Time`=(select max(`Time`) from tb_warn_log where SensorID="+i.toString()+" and Dealed=0)  ");
            sqlBuilder.append(" union ");
        });
        int lIndex=sqlBuilder.lastIndexOf("union");
        String sql=sqlBuilder.substring(0,lIndex);
        return sqlQuery.query(projID,sql,TbWarnLog.class);
    }
}


















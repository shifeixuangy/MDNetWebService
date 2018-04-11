package dataDAO.proj.impl;

import commonUtil.TimeUtil;
import data.proj.FileNameHolder;
import dataDAO.proj.interfaces.NativeSqlQuery;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/15.
 */
public class FileNameQuery {
    public List<FileNameHolder>query(int projID, int sensorID, Timestamp begin,Timestamp end)
    {
        NativeSqlQuery<FileNameHolder> sQuery=new NativeSqlQueryImpl<>();
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append("select ID,FileID,Time from tb_channel_data where SensorID="+Integer.toString(sensorID));
        sqlBuilder.append(" and `Time` between '"+ TimeUtil.format(begin)+"' and '"+TimeUtil.format(end)+"' order by `Time` desc ");
        return sQuery.query(projID,sqlBuilder.toString(),FileNameHolder.class);
    }
}

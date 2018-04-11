package dataDAO.proj.impl;

import commonUtil.CommonVariable;
import commonUtil.SensorDataSelects;
import commonUtil.StringUtil;
import data.common.QueryDensity;
import data.common.SensorType;
import data.proj.SensorData;
import dataDAO.proj.interfaces.NativeSqlQuery;
import dataDAO.proj.interfaces.SensorDataQuery;
import dataDAO.proj.interfaces.SensorNewDataQuery;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/29.
 */
public  class SensorDataQueryImpl<T> implements SensorDataQuery<T>,SensorNewDataQuery {

    protected int projID;
    protected SensorType sensorType;
    private NativeSqlQuery<T>sqlQuery=new NativeSqlQueryImpl<>();
    public SensorDataQueryImpl(int projID, SensorType sType)
    {
        this.projID=projID;
        this.sensorType=sType;
    }

    @Override
    public SensorData getNewData( int sid) {
        String ssid=Integer.toString(sid);
        if(getTableClass()==null)
            return null;
        String tabName=getTableName();
        if(StringUtil.isNullOrEmpty(tabName))
            return null;
        String sql=" select * from "+tabName+" where SensorID="+ssid+"   and  `Time`=(select max(`Time`) from "+tabName+" where SensorID="+ssid+")";
        List results=sqlQuery.query(this.projID,sql,getTableClass());
        List<SensorData>slist=results;
        return slist.size()>0?slist.get(0):null;
    }

    @Override
    public List<SensorData> getNewData(List<Integer> sids) {
        List result=Collections.emptyList();
        if((sids==null)||(sids.size()<=0))
            return result;
        if(sids.size()==1)
            return Arrays.asList(getNewData(sids.get(0)));
        StringBuilder sql=new StringBuilder();
        String tabName=getTableName();
        if(StringUtil.isNullOrEmpty(tabName))
            return result;
        sids.forEach(i->{
            String sid=i.toString();
            sql.append(" select * from "+tabName+"  where SensorID="+sid+"   and  `Time`=(select max(`Time`) from "+tabName+" where SensorID="+sid+")  ");
            sql.append("  union ");
        });
        int lastIndex= sql.lastIndexOf("union");
        String ssql=sql.substring(0,lastIndex);
        result=sqlQuery.query(projID,ssql,getTableClass());
        return result==null? Collections.<SensorData>emptyList():result;
    }

    @Override
    public List<T> getData( Timestamp beginTime, Timestamp endTime,QueryDensity density,  List<Integer> sensorIds) {
       switch (density)
       {
           case Normal:
               return queryAll(beginTime,endTime,sensorIds);
           case Hour:
               return queryHour(beginTime,endTime,sensorIds);
           case Day:
               return queryDay(beginTime,endTime,sensorIds);
           case Week:
               return queryWeek(beginTime,endTime,sensorIds);
           case Month:
               return queryMonth(beginTime,endTime,sensorIds);
           case Year:
               return queryYear(beginTime,endTime,sensorIds);
           default:
               return queryAll(beginTime,endTime,sensorIds);
       }
    }

    public  List<T>queryAll(Timestamp beginTime,Timestamp endTime,List<Integer>sensorIds)
    {
        String sql="select * from "+getTableName()+getWhere(beginTime, endTime, sensorIds);
        return sqlQuery.query(projID,sql,getTableClass());
    }
    public  List<T>queryHour(Timestamp beginTime,Timestamp endTime,List<Integer>sensorIds)
    {
        String sql=SensorDataSelects.getSensorSelect(sensorType)+"  from  "+getTableName()+" "
                +getWhere(beginTime,endTime,sensorIds)+getGroupBy(QueryDensity.Hour);
        return sqlQuery.query(projID,sql,getTableClass());
    }
    public  List<T>queryDay(Timestamp beginTime,Timestamp endTime,List<Integer>sensorIds)
    {
        String sql="select * from  "+getAvgTableName()+getWhere(beginTime,endTime,sensorIds);
        return sqlQuery.query(projID,sql,getTableClass());
    }
    public  List<T>queryWeek(Timestamp beginTime,Timestamp endTime,List<Integer>sensorIds)
    {
        String sql=SensorDataSelects.getSensorSelect(sensorType)+" from "+getAvgTableName()
                +getWhere(beginTime,endTime,sensorIds)+getGroupBy(QueryDensity.Week);
        return sqlQuery.query(projID,sql,getTableClass());
    }
    public  List<T>queryMonth(Timestamp beginTime,Timestamp endTime,List<Integer>sensorIds)
    {
        String sql=SensorDataSelects.getSensorSelect(sensorType)+" from "+getAvgTableName()
                +getWhere(beginTime,endTime,sensorIds)+getGroupBy(QueryDensity.Month);
        return sqlQuery.query(projID,sql,getTableClass());
    }
    public  List<T>queryYear(Timestamp beginTime,Timestamp endTime,List<Integer>sensorIds)
    {
        String sql=SensorDataSelects.getSensorSelect(sensorType)+" from "+getAvgTableName()
                +getWhere(beginTime,endTime,sensorIds)+getGroupBy(QueryDensity.Year);
        return sqlQuery.query(projID,sql,getTableClass());
    }






    private   String getTableName()
    {
        return "   "+SensorDataSelects.getSensorTable(sensorType)+"   ";
    }
    private   String getAvgTableName()
    {
        return "   "+SensorDataSelects.getSensorTable(sensorType)+"_avg   ";
    }

    private String getWhere(Timestamp begin,Timestamp end,List<Integer>sensorIDs)
    {
        StringBuilder where=new StringBuilder(" where ");
        //要判断id
        if((sensorIDs!=null)&&(sensorIDs.size()>0))
        {
            where.append(" SensorID in (");
            sensorIDs.forEach(i->{
                where.append(i.toString()+",");
            });
            where.deleteCharAt(where.lastIndexOf(","));
            where.append(") and ");
        }
        where.append(" `Time` between '"+begin.toString()+"' and '"+end.toString()+"' ");
        return where.toString();
    }

    private String getGroupBy(QueryDensity density)
    {
        String group=" group by SensorID,timestampdiff({re},`Time`,'"+ CommonVariable.BASE_DATETIME+"')  ";
        switch (density)
        {
            case Hour:
                group=group.replace("{re}","hour");
                break;
            case Week:
                group=group.replace("{re}","week");
                break;
            case Month:
                group=group.replace("{re}","month");
                break;
            case Year:
                group=group.replace("{re}","year");
                break;
        }
        return  group;
    }


    public  Class<T>getTableClass()
    {
        return SensorDataSelects.getSensorClass(sensorType);
    }










}

















package dataDAO.proj.impl;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.SensorType;
import data.proj.TbDs2Data;
import data.proj.TbSensorAttri;
import data.proj.TbTpsData;
import dataDAO.ProjQueryHelper;
import dataDAO.proj.interfaces.NativeSqlQuery;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/12/16.
 */
public class DS2Query {
    public List<TbDs2Data> getMinTimeData(int projID, int sensorID)
    {
        List<Integer>sids=new LinkedList<>();
        if(sensorID!= CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            sids.add(sensorID);
        else
        {
            List<TbSensorAttri>tpss= ProjQueryHelper.getSensorList(projID, SensorType.TYPE_DS2.toInt());
            if(CollectionUtil.isNullOrEmpty(tpss))
                return Collections.emptyList();
            sids= tpss.stream().map(t->t.getId()).collect(Collectors.toList());
        }
        StringBuilder sqlBuilder=new StringBuilder();
        sids.forEach(sid->{
            sqlBuilder.append(" select * from tb_ds2_data where SensorID="+sid.toString()+" and `Time`= ");
            sqlBuilder.append(" (select min(`Time`) from tb_ds2_data where SensorID="+sid.toString()+") ");
            sqlBuilder.append("union");
        });
        sqlBuilder.delete(sqlBuilder.lastIndexOf("union"),sqlBuilder.length());
        NativeSqlQuery<TbDs2Data> tpsQuery=new NativeSqlQueryImpl<>();
        return tpsQuery.query(projID,sqlBuilder.toString(),TbDs2Data.class);
    }
}

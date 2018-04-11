package dataDAO.query.helper;

import commonUtil.CommonVariable;
import data.proj.TbTps2KonwnPoint;
import data.proj.TbTps2ObserverStation;
import data.proj.TbTps2Project;
import dataDAO.proj.impl.Tps2KonwPointQuery;
import dataDAO.proj.impl.Tps2ObserverStationQuery;
import dataDAO.proj.impl.Tps2ProjectQuery;

import java.util.List;

/**
 * Created by admin on 2016/4/22.
 */
public class Tps2QueryHelper {
    public static List<TbTps2KonwnPoint>getKonwPointList(int projID,int pointID)
    {
        String where=null;
        if(pointID!= CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where=" ID="+Integer.toString(pointID);
        Tps2KonwPointQuery query=new Tps2KonwPointQuery();
        return query.query(where,projID);
    }

    public static List<TbTps2ObserverStation>getObserverStation(int projID,int stationID)
    {
        String where=null;
        if(stationID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where=" ID="+Integer.toString(stationID);
        Tps2ObserverStationQuery query=new Tps2ObserverStationQuery();
        return query.query(where,projID);
    }

    public static List<TbTps2Project>getProject(int projID,int tps2ProjectID)
    {
        String where=null;
        if(tps2ProjectID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where=" ID="+Integer.toString(tps2ProjectID);
        Tps2ProjectQuery query=new Tps2ProjectQuery();
        return query.query(where,projID);
    }
}

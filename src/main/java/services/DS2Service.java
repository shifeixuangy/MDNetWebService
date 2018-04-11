package services;

import data.proj.TbDs2Data;
import dataDAO.proj.impl.DS2Query;

import java.util.List;

/**
 * Created by liudongdong on 2015/12/16.
 */
public class DS2Service {
    public List<TbDs2Data>getMinTimeDS2Data(int projID,int sensorID)
    {
        DS2Query dq=new DS2Query();
        return dq.getMinTimeData(projID, sensorID);
    }
}

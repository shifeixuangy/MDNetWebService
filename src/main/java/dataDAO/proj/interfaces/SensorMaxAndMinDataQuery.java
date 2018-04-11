package dataDAO.proj.interfaces;

import data.common.QueryDensity;
import data.common.SensorType;
import data.proj.SensorData;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/8/15.
 */
public interface SensorMaxAndMinDataQuery {
    List<SensorData>query(int projID,SensorType sType,List<Integer>sensorIDs,Timestamp begin,Timestamp end,QueryDensity density,boolean isMax);
}

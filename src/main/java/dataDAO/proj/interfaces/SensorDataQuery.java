package dataDAO.proj.interfaces;

import data.common.QueryDensity;
import data.common.SensorType;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/29.
 */
public interface SensorDataQuery<T> {
    List<T>getData(Timestamp beginTime,Timestamp endTime,QueryDensity density,List<Integer>sensorIds);
}

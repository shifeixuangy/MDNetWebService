package dataDAO.proj.interfaces;

import data.proj.ExtremeSensorWarnLogCount;
import data.proj.SensorWarnLogCount;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/6.
 */
public interface SensorWarnLogCountQuery {
    List<SensorWarnLogCount>getLogCount(int projID, Timestamp begin, Timestamp end, List<Integer>sensorIDs);
    List<ExtremeSensorWarnLogCount> getExtremeLogCount(int projID, Timestamp begin, Timestamp end, List<Integer>sensorIDs);
}

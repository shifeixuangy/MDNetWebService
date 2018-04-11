package dataDAO.proj.interfaces;

import data.common.SensorType;
import data.proj.SensorData;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/5.
 */
public interface SensorNewDataQuery {
    public SensorData getNewData(int sid);
    public List<SensorData> getNewData(List<Integer>sids);
}

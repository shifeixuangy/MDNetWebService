package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.SensorData;
import data.proj.ExtremeSensorWarnLogCount;

import java.util.List;

/**
 * Created by liudongdong on 2015/8/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetSensorExtremeDataResult {
    private SensorData maxData;
    private SensorData minData;
    private List<ExtremeSensorWarnLogCount> warnLogCount;

    public SensorData getMaxData() {
        return maxData;
    }

    public void setMaxData(SensorData maxData) {
        this.maxData = maxData;
    }

    public SensorData getMinData() {
        return minData;
    }

    public void setMinData(SensorData minData) {
        this.minData = minData;
    }

    public List<ExtremeSensorWarnLogCount> getWarnLogCount() {
        return warnLogCount;
    }

    public void setWarnLogCount(List<ExtremeSensorWarnLogCount> warnLogCount) {
        this.warnLogCount = warnLogCount;
    }
}

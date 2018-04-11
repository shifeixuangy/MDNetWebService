package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.common.SensorType;
import data.proj.TbSensorAttri;
import data.proj.TbWarnLog;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/5.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class SensorResult {
    @JsonProperty("Sensor_Attri")
    private TbSensorAttri sensorAttri;
    @JsonProperty("Sensor_Data")
    private Object sensorData;
    @JsonProperty("Sensor_WarnLog")
    private TbWarnLog sensorWarnLog;

    public SensorResult()
    {}

    public SensorResult(TbSensorAttri attri,Object sensorData,TbWarnLog warnLog)
    {
        this.sensorAttri=attri;
        this.sensorData=sensorData;
        this.sensorWarnLog=warnLog;
    }


    public TbSensorAttri getSensorAttri() {
        return sensorAttri;
    }

    public void setSensorAttri(TbSensorAttri sensorAttri) {
        this.sensorAttri = sensorAttri;
    }

    public Object getSensorData() {
        return sensorData;
    }

    public void setSensorData(Object sensorData) {
        this.sensorData = sensorData;
    }

    public TbWarnLog getSensorWarnLog() {
        return sensorWarnLog;
    }

    public void setSensorWarnLog(TbWarnLog sensorWarnLog) {
        this.sensorWarnLog = sensorWarnLog;
    }

    public static List<SensorResult> getSensorResults(List<TbSensorAttri>attris,List<Object>data,SensorType sType)
    {
        List<SensorResult>results=new LinkedList<>();

        return  results;
    }


}

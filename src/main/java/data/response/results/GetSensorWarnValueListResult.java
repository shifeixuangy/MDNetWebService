package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbSensorWarnValue;

/**
 * Created by liudongdong on 2015/11/13.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetSensorWarnValueListResult extends TbSensorWarnValue {
    private String sensorName;
    private String sensorAlias;

    public GetSensorWarnValueListResult()
    {}

    public GetSensorWarnValueListResult(TbSensorWarnValue wv)
    {
        setId(wv.getId());
        setSensorID(wv.getSensorID());
        setSensorType(wv.getSensorType());
        setWarnType(wv.getWarnType());
        setWarnLevel(wv.getWarnLevel());
        setValue(wv.getValue());
        setValid(wv.isValid());
        setRemark(wv.getRemark());
    }



    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorAlias() {
        return sensorAlias;
    }

    public void setSensorAlias(String sensorAlias) {
        this.sensorAlias = sensorAlias;
    }
}

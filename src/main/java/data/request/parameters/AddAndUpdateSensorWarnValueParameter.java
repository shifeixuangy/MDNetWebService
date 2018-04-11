package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbSensorWarnValue;

/**
 * Created by liudongdong on 2015/11/13.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateSensorWarnValueParameter {
    private int projID;
    private TbSensorWarnValue sensorWarnValue;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbSensorWarnValue getSensorWarnValue() {
        return sensorWarnValue;
    }

    public void setSensorWarnValue(TbSensorWarnValue sensorWarnValue) {
        this.sensorWarnValue = sensorWarnValue;
    }
}

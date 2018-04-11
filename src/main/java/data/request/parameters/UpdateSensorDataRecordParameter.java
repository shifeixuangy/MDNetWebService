package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/2/18.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class UpdateSensorDataRecordParameter {
    private int projID;
    private int sensorType;
    private String sensorData;
    @JsonProperty("IsAvg")
    private boolean isAvg;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }

    public boolean isAvg() {
        return isAvg;
    }

    public void setAvg(boolean avg) {
        isAvg = avg;
    }
}

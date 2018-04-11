package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/5/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class WarnReviseResult {
    
    private int sensorType;
    
    private int sensorID;
    
    private String sensorName;
    
    private String sensorAlias;
    
    private String warnChildType;
    
    private double value;
    private boolean isValid;

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
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

    public String getWarnChildType() {
        return warnChildType;
    }

    public void setWarnChildType(String warnChildType) {
        this.warnChildType = warnChildType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @JsonProperty("IsValid")
    public boolean isValid() {
        return isValid;
    }

    @JsonProperty("IsValid")
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}

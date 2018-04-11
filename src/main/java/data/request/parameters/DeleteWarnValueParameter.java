package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/5/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DeleteWarnValueParameter {
    
    private int projID;
    
    private int sensorType;
    
    private String warnType;
    
    private int warnLevel;

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

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public int getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }
}

package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/5/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class WarnLogCountResult {
    
    private int projID;
    
    private String projName;
    
    private int sensorType;
    
    private int sensorID;
    
    private String sensorName;
    
    private String sensorAlias;
    
    private int dealedCount;
    
    private int undealedCount;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

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

    public int getDealedCount() {
        return dealedCount;
    }

    public void setDealedCount(int dealedCount) {
        this.dealedCount = dealedCount;
    }

    public int getUndealedCount() {
        return undealedCount;
    }

    public void setUndealedCount(int undealedCount) {
        this.undealedCount = undealedCount;
    }
}

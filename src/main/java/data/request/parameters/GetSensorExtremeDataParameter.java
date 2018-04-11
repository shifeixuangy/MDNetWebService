package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/8/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetSensorExtremeDataParameter {
    private int projID;
    private int sensorType;
    private List<Integer>sensorIDs;
    private Timestamp startTime;
    private Timestamp endTime;
    private int density;

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

    public List<Integer> getSensorIDs() {
        return sensorIDs;
    }

    public void setSensorIDs(List<Integer> sensorIDs) {
        this.sensorIDs = sensorIDs;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}

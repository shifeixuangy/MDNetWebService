package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetChannelDataListByFileParameter {
    private int projID;
    private String fileID;
    private Timestamp time;
    private int sensorID;
    private int channelID;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

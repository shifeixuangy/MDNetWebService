package data.proj;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/12/6.
 */
public class ChannelDataKey {
    private int projID;
    private int sensorID;
    private int channelID;
    private Timestamp dataTime;
    private String fileID;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
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

    public Timestamp getDataTime() {
        return dataTime;
    }

    public void setDataTime(Timestamp dataTime) {
        this.dataTime = dataTime;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChannelDataKey that = (ChannelDataKey) o;

        if (projID != that.projID) return false;
        if (sensorID != that.sensorID) return false;
        if (channelID != that.channelID) return false;
        if (dataTime != null ? !dataTime.equals(that.dataTime) : that.dataTime != null) return false;
        return !(fileID != null ? !fileID.equals(that.fileID) : that.fileID != null);

    }

    @Override
    public int hashCode() {
        int result = projID;
        result = 31 * result + sensorID;
        result = 31 * result + channelID;
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        result = 31 * result + (fileID != null ? fileID.hashCode() : 0);
        return result;
    }
}

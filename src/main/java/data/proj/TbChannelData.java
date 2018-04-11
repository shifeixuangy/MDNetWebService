package data.proj;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/12/4.
 */
@Entity
@Table(name = "tb_channel_data", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbChannelData implements SensorData{
    @JsonProperty("ID")
    private int id;
    @JsonProperty("SensorID")
    private int sensorID;
    private int channelID;
    private String fileID;
    private Timestamp time;
    private Integer dataCount;
    @JsonIgnore()
    private Float recordLength;
    private String data;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "SensorID")
    public int getSensorId() {
        return sensorID;
    }

    public void setSensorId(int sensorID) {
        this.sensorID = sensorID;
    }
    @Basic
    @Column(name = "ChannelID")
    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    @Basic
    @Column(name = "FileID")
    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    @Basic
    @Column(name = "`Time`")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    @Basic
    @Column(name = "DataCount")
    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    @Basic
    @Column(name = "RecordLength")
    public Float getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(Float recordLength) {
        this.recordLength = recordLength;
    }

    @Basic
    @Column(name = "`Data`",length  =2100000)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbChannelData that = (TbChannelData) o;

        if (id != that.id) return false;
        if (sensorID != that.sensorID) return false;
        if (channelID != that.channelID) return false;
        if (!fileID.equals(that.fileID)) return false;
        if (!time.equals(that.time)) return false;
        if (dataCount != null ? !dataCount.equals(that.dataCount) : that.dataCount != null) return false;
        if (recordLength != null ? !recordLength.equals(that.recordLength) : that.recordLength != null) return false;
        return !(data != null ? !data.equals(that.data) : that.data != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sensorID;
        result = 31 * result + channelID;
        result = 31 * result + fileID.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + (dataCount != null ? dataCount.hashCode() : 0);
        result = 31 * result + (recordLength != null ? recordLength.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}

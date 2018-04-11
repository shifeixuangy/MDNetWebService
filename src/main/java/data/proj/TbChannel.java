package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/12/4.
 */
@Entity
@Table(name = "tb_channel", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbChannel {
    @JsonProperty("ID")
    private int id;
    private int sensorID;
    private String channelName;
    private  Float triggerLevel;
    private  Float sensitivity;
    private  Float shifting;
    private Integer Mzx;
    private Integer Lzx;
    private String unit;
    private Integer range;
    private String triggerMode;

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
    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    @Basic
    @Column(name = "ChannelName")
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Basic
    @Column(name = "TriggerLevel")
    public Float getTriggerLevel() {
        return triggerLevel;
    }

    public void setTriggerLevel(Float triggerLevel) {
        this.triggerLevel = triggerLevel;
    }
    @Basic
    @Column(name = "Sensitivity")
    public Float getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(Float sensitivity) {
        this.sensitivity = sensitivity;
    }
    @Basic
    @Column(name = "Shifting")
    public Float getShifting() {
        return shifting;
    }

    public void setShifting(Float shifting) {
        this.shifting = shifting;
    }
    @Basic
    @Column(name = "Mzx")
    public Integer getMzx() {
        return Mzx;
    }

    public void setMzx(Integer mzx) {
        Mzx = mzx;
    }
    @Basic
    @Column(name = "Lzx")
    public Integer getLzx() {
        return Lzx;
    }

    public void setLzx(Integer lzx) {
        Lzx = lzx;
    }
    @Basic
    @Column(name = "Unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    @Basic
    @Column(name = "`Range`")
    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
    @Basic
    @Column(name = "TriggerMode")
    public String getTriggerMode() {
        return triggerMode;
    }

    public void setTriggerMode(String triggerMode) {
        this.triggerMode = triggerMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbChannel tbChannel = (TbChannel) o;

        if (id != tbChannel.id) return false;
        if (sensorID != tbChannel.sensorID) return false;
        if (!channelName.equals(tbChannel.channelName)) return false;
        if (triggerLevel != null ? !triggerLevel.equals(tbChannel.triggerLevel) : tbChannel.triggerLevel != null)
            return false;
        if (sensitivity != null ? !sensitivity.equals(tbChannel.sensitivity) : tbChannel.sensitivity != null)
            return false;
        if (shifting != null ? !shifting.equals(tbChannel.shifting) : tbChannel.shifting != null) return false;
        if (Mzx != null ? !Mzx.equals(tbChannel.Mzx) : tbChannel.Mzx != null) return false;
        if (Lzx != null ? !Lzx.equals(tbChannel.Lzx) : tbChannel.Lzx != null) return false;
        if (unit != null ? !unit.equals(tbChannel.unit) : tbChannel.unit != null) return false;
        if (range != null ? !range.equals(tbChannel.range) : tbChannel.range != null) return false;
        return !(triggerMode != null ? !triggerMode.equals(tbChannel.triggerMode) : tbChannel.triggerMode != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sensorID;
        result = 31 * result + channelName.hashCode();
        result = 31 * result + (triggerLevel != null ? triggerLevel.hashCode() : 0);
        result = 31 * result + (sensitivity != null ? sensitivity.hashCode() : 0);
        result = 31 * result + (shifting != null ? shifting.hashCode() : 0);
        result = 31 * result + (Mzx != null ? Mzx.hashCode() : 0);
        result = 31 * result + (Lzx != null ? Lzx.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (triggerMode != null ? triggerMode.hashCode() : 0);
        return result;
    }
}

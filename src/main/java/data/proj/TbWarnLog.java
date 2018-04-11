package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_warn_log", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbWarnLog {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("SensorID")
    private Integer sensorId;
    private Integer sensorType;
    private Timestamp time;
    private String warnMainType;
    private String warnChildType;
    private Short warnLevel;
    private String sendMessage;
    private Boolean sendFlag;
    private Boolean dealed;
    private String dealName;
    private String dealPhone;

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
    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    @Basic
    @Column(name = "SensorType")
    public Integer getSensorType() {
        return sensorType;
    }

    public void setSensorType(Integer sensorType) {
        this.sensorType = sensorType;
    }

    @Basic
    @Column(name = "Time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "WarnMainType")
    public String getWarnMainType() {
        return warnMainType;
    }

    public void setWarnMainType(String warnMainType) {
        this.warnMainType = warnMainType;
    }

    @Basic
    @Column(name = "WarnChildType")
    public String getWarnChildType() {
        return warnChildType;
    }

    public void setWarnChildType(String warnChildType) {
        this.warnChildType = warnChildType;
    }

    @Basic
    @Column(name = "WarnLevel")
    public Short getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Short warnLevel) {
        this.warnLevel = warnLevel;
    }

    @Basic
    @Column(name = "SendMessage")
    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Basic
    @Column(name = "SendFlag")
    public Boolean getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Boolean sendFlag) {
        this.sendFlag = sendFlag;
    }

    @Basic
    @Column(name = "Dealed")
    public Boolean getDealed() {
        return dealed;
    }

    public void setDealed(Boolean dealed) {
        this.dealed = dealed;
    }

    @Basic
    @Column(name = "DealName")
    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    @Basic
    @Column(name = "DealPhone")
    public String getDealPhone() {
        return dealPhone;
    }

    public void setDealPhone(String dealPhone) {
        this.dealPhone = dealPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbWarnLog tbWarnLog = (TbWarnLog) o;

        if (id != tbWarnLog.id) return false;
        if (dealName != null ? !dealName.equals(tbWarnLog.dealName) : tbWarnLog.dealName != null) return false;
        if (dealPhone != null ? !dealPhone.equals(tbWarnLog.dealPhone) : tbWarnLog.dealPhone != null) return false;
        if (dealed != null ? !dealed.equals(tbWarnLog.dealed) : tbWarnLog.dealed != null) return false;
        if (sendFlag != null ? !sendFlag.equals(tbWarnLog.sendFlag) : tbWarnLog.sendFlag != null) return false;
        if (sendMessage != null ? !sendMessage.equals(tbWarnLog.sendMessage) : tbWarnLog.sendMessage != null)
            return false;
        if (sensorId != null ? !sensorId.equals(tbWarnLog.sensorId) : tbWarnLog.sensorId != null) return false;
        if (sensorType != null ? !sensorType.equals(tbWarnLog.sensorType) : tbWarnLog.sensorType != null) return false;
        if (time != null ? !time.equals(tbWarnLog.time) : tbWarnLog.time != null) return false;
        if (warnChildType != null ? !warnChildType.equals(tbWarnLog.warnChildType) : tbWarnLog.warnChildType != null)
            return false;
        if (warnLevel != null ? !warnLevel.equals(tbWarnLog.warnLevel) : tbWarnLog.warnLevel != null) return false;
        if (warnMainType != null ? !warnMainType.equals(tbWarnLog.warnMainType) : tbWarnLog.warnMainType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sensorId != null ? sensorId.hashCode() : 0);
        result = 31 * result + (sensorType != null ? sensorType.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (warnMainType != null ? warnMainType.hashCode() : 0);
        result = 31 * result + (warnChildType != null ? warnChildType.hashCode() : 0);
        result = 31 * result + (warnLevel != null ? warnLevel.hashCode() : 0);
        result = 31 * result + (sendMessage != null ? sendMessage.hashCode() : 0);
        result = 31 * result + (sendFlag != null ? sendFlag.hashCode() : 0);
        result = 31 * result + (dealed != null ? dealed.hashCode() : 0);
        result = 31 * result + (dealName != null ? dealName.hashCode() : 0);
        result = 31 * result + (dealPhone != null ? dealPhone.hashCode() : 0);
        return result;
    }
}

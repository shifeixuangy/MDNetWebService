package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbWarnLog;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/5/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetWarnLogListResult {

    private int ID;
    private Integer sensorID;
    
    private String sensorName;
    
    private String sensorAlias;
    
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

    public GetWarnLogListResult()
    {}

    public GetWarnLogListResult(TbWarnLog warnLog)
    {
        this.ID=warnLog.getId();
        this.sensorID=warnLog.getSensorId();
        this.sensorType=warnLog.getSensorType();
        this.time=warnLog.getTime();
        this.warnMainType=warnLog.getWarnMainType();
        this.warnChildType=warnLog.getWarnChildType();
        this.warnLevel=warnLog.getWarnLevel();
        this.sendMessage=warnLog.getSendMessage();
        this.sendFlag=warnLog.getSendFlag();
        this.dealed=warnLog.getDealed();
        this.dealName=warnLog.getDealName();
        this.dealPhone=warnLog.getDealPhone();
    }

    @JsonProperty("ID")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Integer getSensorID() {
        return sensorID;
    }

    public void setSensorID(Integer sensorID) {
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

    public Integer getSensorType() {
        return sensorType;
    }

    public void setSensorType(Integer sensorType) {
        this.sensorType = sensorType;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getWarnMainType() {
        return warnMainType;
    }

    public void setWarnMainType(String warnMainType) {
        this.warnMainType = warnMainType;
    }

    public String getWarnChildType() {
        return warnChildType;
    }

    public void setWarnChildType(String warnChildType) {
        this.warnChildType = warnChildType;
    }

    public Short getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Short warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public Boolean getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Boolean sendFlag) {
        this.sendFlag = sendFlag;
    }

    public Boolean getDealed() {
        return dealed;
    }

    public void setDealed(Boolean dealed) {
        this.dealed = dealed;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealPhone() {
        return dealPhone;
    }

    public void setDealPhone(String dealPhone) {
        this.dealPhone = dealPhone;
    }
}

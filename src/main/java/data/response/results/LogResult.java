package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.common.TbLog;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/5/10.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class LogResult {
    @JsonProperty("UID")
    private Integer uid;
    private String userName;
    private Integer projID;
    private String projName;
    private Timestamp time;
    private int logType;
    private String logMsg;


    public LogResult()
    {}

    public LogResult(TbLog log)
    {
        this.uid=log.getUid();
        this.projID=log.getProjId();
        this.logType=log.getLogType();
        this.logMsg=log.getLogMsg();
        this.time=log.getTime();
    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProjID() {
        return projID;
    }

    public void setProjID(Integer projID) {
        this.projID = projID;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }
}

package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/4/17.
 */
@Entity
@Table(name = "tb_log", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbLog {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("UID")
    private Integer uid;
    @JsonProperty("ProjID")
    private Integer projId;
    private Timestamp time;
    private Short logType;
    private String logMsg;

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
    @Column(name = "UID")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "ProjID")
    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
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
    @Column(name = "LogType")
    public Short getLogType() {
        return logType;
    }

    public void setLogType(Short logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "LogMsg")
    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLog tbLog = (TbLog) o;

        if (id != tbLog.id) return false;
        if (logMsg != null ? !logMsg.equals(tbLog.logMsg) : tbLog.logMsg != null) return false;
        if (logType != null ? !logType.equals(tbLog.logType) : tbLog.logType != null) return false;
        if (projId != null ? !projId.equals(tbLog.projId) : tbLog.projId != null) return false;
        if (time != null ? !time.equals(tbLog.time) : tbLog.time != null) return false;
        if (uid != null ? !uid.equals(tbLog.uid) : tbLog.uid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (projId != null ? projId.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (logType != null ? logType.hashCode() : 0);
        result = 31 * result + (logMsg != null ? logMsg.hashCode() : 0);
        return result;
    }
}

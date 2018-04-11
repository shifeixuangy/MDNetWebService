package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/5/10.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetLogListParameter {
    @JsonProperty("UID")
    private int uid;
    @Min(-1)
    private int projID;
    @NotNull
    private Timestamp startTime;
    @NotNull
    private Timestamp endTime;
    @Min(1)
    private int page;
    @Min(1)
    private int pageSize;
    private  short logType;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public short getLogType() {
        return logType;
    }

    public void setLogType(short logType) {
        this.logType = logType;
    }
}

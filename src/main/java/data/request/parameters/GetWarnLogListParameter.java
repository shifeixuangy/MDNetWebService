package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.ParameterValidator;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/5/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetWarnLogListParameter implements ParameterValidator{

    public GetWarnLogListParameter() {
        warnType=-1;
    }

    @Override
    public boolean validate() {
        return (startTime!=null)&&(endTime!=null)&&(page>=1)&&(pageSize>0);
    }

    
    private int projID;
    
    private int sensorType;
    
    private int sensorID;
    
    private Timestamp startTime;
    
    private Timestamp endTime;
    
    private int dealed;
    
    private int page;
    
    private int pageSize;

    private int warnType;

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

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
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

    public int getDealed() {
        return dealed;
    }

    public void setDealed(int dealed) {
        this.dealed = dealed;
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

    public int getWarnType() {
        return warnType;
    }

    public void setWarnType(int warnType) {
        this.warnType = warnType;
    }
}

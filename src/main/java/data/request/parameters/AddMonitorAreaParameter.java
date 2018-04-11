package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbMonitorArea;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddMonitorAreaParameter {
    
    private int projID;
    
    private String areaName;
    
    private String centerPoint;
    
    private String boundary;
    
    private String note;

    public TbMonitorArea toTbMonitorArea()
    {
        TbMonitorArea ma=new TbMonitorArea();
        ma.setAreaName(this.areaName);
        ma.setCenterPoint(this.centerPoint);
        ma.setBoundary(this.boundary);
        ma.setNote(this.note);
        return  ma;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(String centerPoint) {
        this.centerPoint = centerPoint;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

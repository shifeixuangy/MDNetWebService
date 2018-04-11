package data.response.results;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import commonUtil.CommonVariable;
import data.common.TbProjInfo;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/5/10.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetProjListResult {
    
    private int projID;
    private String name;
    private String connectString;
    private String note;
    private int regionID;
    private String centerPoint;
    private String boundary;
    private Timestamp buildTime;
    private String location;
    private String type;
    private String grade;
    private String status;
    private Integer levelID;
    private Integer projTypeID;
    private int lastWeekWarnNum;

    public GetProjListResult()
    {}

    public GetProjListResult(TbProjInfo pi)
    {
        this.projID=pi.getId();
        this.name=pi.getName();
        this.connectString=pi.getConnectString();
        this.note=pi.getNote();
        this.regionID=(pi.getRegionId()==null)? CommonVariable.DEFAULT_AUTO_INCREMENT_ID:pi.getRegionId();
        this.centerPoint=pi.getCenterPoint();
        this.boundary=pi.getBoundary();
        this.buildTime=pi.getBuildTime();
        this.location=pi.getLocation();
        this.type=pi.getType();
        this.grade=pi.getGrade();
        this.status=pi.getStatus();
        this.levelID=pi.getLevelID();
        this.projTypeID=pi.getProjTypeID();
    }
    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
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

    public Timestamp getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Timestamp buildTime) {
        this.buildTime = buildTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }

    public Integer getProjTypeID() {
        return projTypeID;
    }

    public void setProjTypeID(Integer projTypeID) {
        this.projTypeID = projTypeID;
    }

    public int getLastWeekWarnNum() {
        return lastWeekWarnNum;
    }

    public void setLastWeekWarnNum(int lastWeekWarnNum) {
        this.lastWeekWarnNum = lastWeekWarnNum;
    }
}

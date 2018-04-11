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
@Table(name = "tb_proj_info", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbProjInfo {
    @JsonProperty("ID")
    private int id;
    private String name;
    private String connectString;
    @JsonProperty("IsValid")
    private boolean isValid;
    private String note;
    @JsonProperty("RegionID")
    private Integer regionId;
    private String centerPoint;
    private String boundary;
    private Timestamp buildTime;
    private String location;
    private String type;
    private String grade;
    private String status;
    private Integer levelID;
    private Integer projTypeID;

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
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ConnectString")
    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    @Basic
    @Column(name = "IsValid")
    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Basic
    @Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "RegionID")
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "CenterPoint")
    public String getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(String centerPoint) {
        this.centerPoint = centerPoint;
    }

    @Basic
    @Column(name = "Boundary")
    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    @Basic
    @Column(name = "BuildTime")
    public Timestamp getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Timestamp buildTime) {
        this.buildTime = buildTime;
    }

    @Basic
    @Column(name = "Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "LevelID")
    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }

    @Basic
    @Column(name = "ProjTypeID")
    public Integer getProjTypeID() {
        return projTypeID;
    }

    public void setProjTypeID(Integer projTypeID) {
        this.projTypeID = projTypeID;
    }

    public TbProjInfo clone()
    {
        TbProjInfo pi=new TbProjInfo();
        pi.setId(this.getId());
        pi.setName(this.getName());
        pi.setConnectString(this.getConnectString());
        pi.setIsValid(this.getIsValid());
        pi.setNote(this.getNote());
        pi.setRegionId(this.getRegionId());
        pi.setCenterPoint(this.getCenterPoint());
        pi.setBoundary(this.getBoundary());
        pi.setBuildTime(this.getBuildTime());
        pi.setLocation(this.getLocation());
        pi.setType(this.getType());
        pi.setGrade(this.getGrade());
        pi.setStatus(this.getStatus());
        pi.setLevelID(this.getLevelID());
        pi.setProjTypeID(this.getProjTypeID());
        return pi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbProjInfo that = (TbProjInfo) o;

        if (id != that.id) return false;
        if (isValid != that.isValid) return false;
        if (!name.equals(that.name)) return false;
        if (connectString != null ? !connectString.equals(that.connectString) : that.connectString != null)
            return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;
        if (centerPoint != null ? !centerPoint.equals(that.centerPoint) : that.centerPoint != null) return false;
        if (boundary != null ? !boundary.equals(that.boundary) : that.boundary != null) return false;
        if (buildTime != null ? !buildTime.equals(that.buildTime) : that.buildTime != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (levelID != null ? !levelID.equals(that.levelID) : that.levelID != null) return false;
        return projTypeID != null ? projTypeID.equals(that.projTypeID) : that.projTypeID == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (connectString != null ? connectString.hashCode() : 0);
        result = 31 * result + (isValid ? 1 : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (centerPoint != null ? centerPoint.hashCode() : 0);
        result = 31 * result + (boundary != null ? boundary.hashCode() : 0);
        result = 31 * result + (buildTime != null ? buildTime.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (levelID != null ? levelID.hashCode() : 0);
        result = 31 * result + (projTypeID != null ? projTypeID.hashCode() : 0);
        return result;
    }
}

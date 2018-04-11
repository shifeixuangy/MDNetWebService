package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_monitor_area", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbMonitorArea {
    @JsonProperty("ID")
    private int id;
    private String areaName;
    private String centerPoint;
    private String boundary;
    private String note;

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
    @Column(name = "AreaName")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
    @Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbMonitorArea that = (TbMonitorArea) o;

        if (id != that.id) return false;
        if (areaName != null ? !areaName.equals(that.areaName) : that.areaName != null) return false;
        if (boundary != null ? !boundary.equals(that.boundary) : that.boundary != null) return false;
        if (centerPoint != null ? !centerPoint.equals(that.centerPoint) : that.centerPoint != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        result = 31 * result + (centerPoint != null ? centerPoint.hashCode() : 0);
        result = 31 * result + (boundary != null ? boundary.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}

package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/17.
 */
@Entity
@Table(name = "tb_region", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbRegion {
    @JsonProperty("ID")
    private int id;
    private String regionName;
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
    @Column(name = "RegionName")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

        TbRegion tbRegion = (TbRegion) o;

        if (id != tbRegion.id) return false;
        if (boundary != null ? !boundary.equals(tbRegion.boundary) : tbRegion.boundary != null) return false;
        if (centerPoint != null ? !centerPoint.equals(tbRegion.centerPoint) : tbRegion.centerPoint != null)
            return false;
        if (note != null ? !note.equals(tbRegion.note) : tbRegion.note != null) return false;
        if (regionName != null ? !regionName.equals(tbRegion.regionName) : tbRegion.regionName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        result = 31 * result + (centerPoint != null ? centerPoint.hashCode() : 0);
        result = 31 * result + (boundary != null ? boundary.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}

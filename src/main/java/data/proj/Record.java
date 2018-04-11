package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/11/2.
 */
@Entity
@Table(name = "record", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class Record {
    @JsonProperty("ID")
    private int id;
    private int checkID;
    private Double lat;
    private Double lng;
    private Timestamp recordTime;
    private Timestamp uploadTime;
    private int securityStatus;
    private String regionStatus;
    private String aroundStatus;
    private Integer regionID;

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
    @Column(name = "CheckID")
    public int getCheckID() {
        return checkID;
    }

    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }

    @Basic
    @Column(name = "Lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "Lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
    @Basic
    @Column(name = "RecordTime")
    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    @Basic
    @Column(name = "UploadTime")
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "SecurityStatus")
    public int getSecurityStatus() {
        return securityStatus;
    }


    public void setSecurityStatus(int securityStatus) {
        this.securityStatus = securityStatus;
    }

    @Basic
    @Column(name = "RegionStatus")
    public String getRegionStatus() {
        return regionStatus;
    }

    public void setRegionStatus(String regionStatus) {
        this.regionStatus = regionStatus;
    }
    @Basic
    @Column(name = "AroundStatus")
    public String getAroundStatus() {
        return aroundStatus;
    }

    public void setAroundStatus(String aroundStatus) {
        this.aroundStatus = aroundStatus;
    }

    @Basic
    @Column(name = "RegionID")
    public Integer getRegionID() {
        return regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (checkID != record.checkID) return false;
        if (id != record.id) return false;
        if (securityStatus != record.securityStatus) return false;
        if (aroundStatus != null ? !aroundStatus.equals(record.aroundStatus) : record.aroundStatus != null)
            return false;
        if (lat != null ? !lat.equals(record.lat) : record.lat != null) return false;
        if (lng != null ? !lng.equals(record.lng) : record.lng != null) return false;
        if (!recordTime.equals(record.recordTime)) return false;
        if (regionID != null ? !regionID.equals(record.regionID) : record.regionID != null) return false;
        if (regionStatus != null ? !regionStatus.equals(record.regionStatus) : record.regionStatus != null)
            return false;
        if (uploadTime != null ? !uploadTime.equals(record.uploadTime) : record.uploadTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + checkID;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + recordTime.hashCode();
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + securityStatus;
        result = 31 * result + (regionStatus != null ? regionStatus.hashCode() : 0);
        result = 31 * result + (aroundStatus != null ? aroundStatus.hashCode() : 0);
        result = 31 * result + (regionID != null ? regionID.hashCode() : 0);
        return result;
    }
}

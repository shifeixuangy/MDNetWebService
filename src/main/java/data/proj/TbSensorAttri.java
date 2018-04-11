package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_sensor_attri", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbSensorAttri {
    @JsonProperty("ID")
    private int id;
    private String name;
    private String alias;
    private String location;
    private int status;
    @JsonProperty("GroupID")
    private Integer groupId;
    private String note;
    private int sensorType;
    @JsonProperty("IsValid")
    private boolean isValid;
    private String exValues;

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
    @Column(name = "Alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
    @Column(name = "Status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "GroupID")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
    @Column(name = "SensorType")
    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
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
    @Column(name = "ExValues")
    public String getExValues() {
        return exValues;
    }

    public void setExValues(String exValues) {
        this.exValues = exValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbSensorAttri)) return false;

        TbSensorAttri that = (TbSensorAttri) o;

        if (id != that.id) return false;
        if (isValid != that.isValid) return false;
        if (sensorType != that.sensorType) return false;
        if (status != that.status) return false;
        if (alias != null ? !alias.equals(that.alias) : that.alias != null) return false;
        if (exValues != null ? !exValues.equals(that.exValues) : that.exValues != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + sensorType;
        result = 31 * result + (isValid ? 1 : 0);
        result = 31 * result + (exValues != null ? exValues.hashCode() : 0);
        return result;
    }
}

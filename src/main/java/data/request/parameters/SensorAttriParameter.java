package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbSensorAttri;

/**
 * Created by liudongdong on 2015/5/4.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class SensorAttriParameter {

    private int projID;

    private int sensorID;

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }


    private int sensorType;

    private String name;

    private String alias;

    private String location;

    private int status;

    private Integer groupID;

    private String note;

    private boolean isValid;

    private String exValues;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty("IsValid")
    public boolean isValid() {
        return isValid;
    }

    @JsonProperty("IsValid")
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getExValues() {
        return exValues;
    }

    public void setExValues(String exValues) {
        this.exValues = exValues;
    }

    public TbSensorAttri toSensorAttri() {
        TbSensorAttri sa = new TbSensorAttri();
        sa.setId(this.sensorID);
        sa.setSensorType(this.sensorType);
        sa.setName(this.name);
        sa.setAlias(this.alias);
        sa.setLocation(this.location);
        sa.setStatus(this.status);
        sa.setGroupId(this.groupID);
        sa.setNote(this.note);
        sa.setIsValid(this.isValid);
        sa.setExValues(this.exValues);
        return sa;
    }
}























package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbGroup;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddGroupParameter {

    private int projID;

    private int sensorType;

    private String name;

    private String alias;

    private String note;
    private String exValues;

    public TbGroup toTbGroup() {
        TbGroup g = new TbGroup();
        g.setSensorType(this.sensorType);
        g.setName(this.name);
        g.setAlias(this.alias);
        g.setNote(this.note);
        g.setExValues(this.exValues);
        return g;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getExValues() {
        return exValues;
    }

    public void setExValues(String exValues) {
        this.exValues = exValues;
    }
}

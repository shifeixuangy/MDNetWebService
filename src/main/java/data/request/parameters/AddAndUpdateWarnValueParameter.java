package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbWarnValue;

/**
 * Created by liudongdong on 2015/5/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateWarnValueParameter {
    private int projID;
    private int sensorType;
    private String warnType;
    private int warnLevel;
    private double value;
    @JsonProperty("IsValid")
    private boolean isValid;

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

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public int getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public TbWarnValue toWarnValue()
    {
        TbWarnValue wv=new TbWarnValue();
        wv.setSensorType(this.sensorType);
        wv.setWarnType(this.warnType);
        wv.setWarnLevel((short)this.warnLevel);
        wv.setValue((float)this.value);
        wv.setValid(this.isValid);
        return  wv;
    }

}






















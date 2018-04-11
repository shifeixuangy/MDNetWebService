package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbWarnRevise;

/**
 * Created by liudongdong on 2015/5/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateWarnReviseParameter {
    private int projID;
    private int sensorType;
    private int sensorID;
    private String warnChildType;
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

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    public String getWarnChildType() {
        return warnChildType;
    }

    public void setWarnChildType(String warnChildType) {
        this.warnChildType = warnChildType;
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

    public TbWarnRevise toWarnRevise()
    {
        TbWarnRevise wr=new TbWarnRevise();
        wr.setSensorId(this.sensorID);
        wr.setWarnChildType(this.warnChildType);
        wr.setValue((float)this.value);
        wr.setValid(this.isValid);
        return  wr;
    }

}





















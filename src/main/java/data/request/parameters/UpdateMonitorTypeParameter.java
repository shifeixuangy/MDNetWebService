package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbMonitorType;

/**
 * Created by liudongdong on 2016/3/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class UpdateMonitorTypeParameter {
    private int projID;
    @JsonProperty("ID")
    private int ID;
    private int sensorType;
    private String typeName;
    private String name;
    @JsonProperty("IsValid")
    private boolean isValid;
    private String values;
    private int displayOrder;
    private int showInterval;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getShowInterval() {
        return showInterval;
    }

    public void setShowInterval(int showInterval) {
        this.showInterval = showInterval;
    }

    public TbMonitorType toTbMonitorType()
    {
        TbMonitorType mt=new TbMonitorType();
        mt.setId(this.ID);
        mt.setName(this.name);
        mt.setSensorType(this.sensorType);
        mt.setTypeName(this.typeName);
        mt.setValid(this.isValid);
        mt.setValues(this.values);
        mt.setDisplayOrder(this.displayOrder);
        mt.setShowInterval(this.showInterval);
        return mt;
    }
}

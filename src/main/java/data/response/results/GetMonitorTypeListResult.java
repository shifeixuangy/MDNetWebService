package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbMonitorType;

import java.util.Comparator;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetMonitorTypeListResult {
    @JsonProperty("ID")
    private int id;
    private int sensorType;
    
    private String typeName;
    
    private String name;
    @JsonProperty("IsValid")
    private boolean isValid;
    private int displayOrder;
    private int showInterval;
    
    private String values;
    
    private int sensorCount;

    public GetMonitorTypeListResult()
    {}
    public GetMonitorTypeListResult(TbMonitorType mt)
    {
        this.id=mt.getId();
        this.sensorType=mt.getSensorType();
        this.typeName=mt.getTypeName();
        this.name=mt.getName();
        this.isValid=mt.isValid();
        this.values=mt.getValues();
        this.displayOrder=mt.getDisplayOrder();
        this.showInterval=mt.getShowInterval();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public int getSensorCount() {
        return sensorCount;
    }

    public void setSensorCount(int sensorCount) {
        this.sensorCount = sensorCount;
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

    public static Comparator<GetMonitorTypeListResult>COMPARATOR=new Comparator<GetMonitorTypeListResult>() {
        @Override
        public int compare(GetMonitorTypeListResult o1, GetMonitorTypeListResult o2) {
            return o1.displayOrder-o2.displayOrder;
        }
    };
}

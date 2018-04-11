package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbMonitorArea;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class UpdateMonitorAreaParameter extends AddMonitorAreaParameter {
    
    private int areaID;

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    @Override
    public TbMonitorArea toTbMonitorArea() {
        TbMonitorArea ma= super.toTbMonitorArea();
        ma.setId(this.areaID);
        return  ma;
    }
}

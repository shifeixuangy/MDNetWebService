package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.common.TbRegion;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddRegionParameter {
    
    private String regionName;
    
    private String centerPoint;
    
    private String boundary;
    
    private String note;

    public TbRegion toTbRegion()
    {
        TbRegion re=new TbRegion();
        re.setRegionName(this.regionName);
        re.setCenterPoint(this.centerPoint);
        re.setBoundary(this.boundary);
        re.setNote(this.note);
        return  re;
    }


    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(String centerPoint) {
        this.centerPoint = centerPoint;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

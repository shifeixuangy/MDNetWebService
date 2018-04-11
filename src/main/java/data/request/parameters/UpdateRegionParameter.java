package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.common.TbRegion;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class UpdateRegionParameter extends AddRegionParameter {
    
    private int regionID;

    @Override
    public TbRegion toTbRegion() {
        TbRegion re= super.toTbRegion();
        re.setId(this.regionID);
        return  re;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }
}

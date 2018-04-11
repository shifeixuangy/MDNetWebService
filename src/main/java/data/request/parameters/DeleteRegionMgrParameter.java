package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DeleteRegionMgrParameter {
    @JsonProperty("UID")
    private  int uid;
    
    private int regionID;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }
}

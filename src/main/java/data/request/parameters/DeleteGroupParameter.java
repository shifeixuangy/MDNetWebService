package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbGroup;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DeleteGroupParameter {
    
    private int projID;
    
    private int groupID;

    public TbGroup toTbGroup()
    {
        TbGroup g=new TbGroup();
        g.setId(this.groupID);
        return g;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}

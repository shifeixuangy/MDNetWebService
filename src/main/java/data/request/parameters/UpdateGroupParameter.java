package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbGroup;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class UpdateGroupParameter  {

    
    private int projID;
    
    private int groupID;
    
    private String alias;
    
    private String note;
    private String exValues;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getExValues() {
        return exValues;
    }

    public void setExValues(String exValues) {
        this.exValues = exValues;
    }
}

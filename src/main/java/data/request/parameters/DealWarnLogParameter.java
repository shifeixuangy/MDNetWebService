package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/7/16.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DealWarnLogParameter {
    private int projID;
    @JsonProperty("ID")
    private int ID;
    private String dealName;
    private String DealPhone;
    private String note;

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

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealPhone() {
        return DealPhone;
    }

    public void setDealPhone(String dealPhone) {
        DealPhone = dealPhone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

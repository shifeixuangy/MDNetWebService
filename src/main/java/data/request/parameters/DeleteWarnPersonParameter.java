package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DeleteWarnPersonParameter {
    
    private int projID;
    
    private String userName;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

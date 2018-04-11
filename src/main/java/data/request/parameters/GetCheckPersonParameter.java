package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/11/4.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetCheckPersonParameter {
    private int projID;
    private int checkID;
    private int userID;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getCheckID() {
        return checkID;
    }

    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

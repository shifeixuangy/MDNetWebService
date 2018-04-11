package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/11/16.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetProjUserListResult {
    private int userID;
    private String userName;
    @JsonProperty("IsAdmin")
    private boolean isAdmin;

    public GetProjUserListResult() {
    }

    public GetProjUserListResult(int userID, String userName,boolean isAdmin) {
        this.userID = userID;
        this.userName = userName;
        this.isAdmin=isAdmin;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

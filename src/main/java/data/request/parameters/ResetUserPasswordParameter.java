package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/8/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ResetUserPasswordParameter {
    @JsonProperty("UID")
    private int UID;
    private String newPassword;

    public int getUID() {
        return UID;
    }

    public void setUID(int uID) {
        this.UID = uID;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

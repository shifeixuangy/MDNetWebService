package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/3/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class SetUserHeadPhotoParameter {
    private int userID;
    private String photoName;
    private String photoContent;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoContent() {
        return photoContent;
    }

    public void setPhotoContent(String photoContent) {
        this.photoContent = photoContent;
    }
}

package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/11/4.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DeleteCheckVideoParameter {
    private int projID;
    private int videoID;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
    }
}

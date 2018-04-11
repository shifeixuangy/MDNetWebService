package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/1/14.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ImageWrapper {
    private int imageID;
    private String imagePath;
    private String imageNote;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageNote() {
        return imageNote;
    }

    public void setImageNote(String imageNote) {
        this.imageNote = imageNote;
    }
}

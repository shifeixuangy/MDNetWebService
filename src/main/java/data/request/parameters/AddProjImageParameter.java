package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/1/14.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddProjImageParameter {
    private int projID;
    private String imageName;
    private String imageContent;
    private String imageNote;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    public String getImageNote() {
        return imageNote;
    }

    public void setImageNote(String imageNote) {
        this.imageNote = imageNote;
    }
}

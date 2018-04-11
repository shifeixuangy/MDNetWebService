package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * Created by liudongdong on 2016/1/14.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ProjImageWrapper {
    private int projID;
    private List<ImageWrapper>projImages;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public List<ImageWrapper> getProjImages() {
        return projImages;
    }

    public void setProjImages(List<ImageWrapper> projImages) {
        this.projImages = projImages;
    }
}

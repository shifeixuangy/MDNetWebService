package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbTps2KonwnPoint;

/**
 * Created by admin on 2016/4/22.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateTps2KnowPointParameter {
    private int projID;
    private TbTps2KonwnPoint data;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbTps2KonwnPoint getData() {
        return data;
    }

    public void setData(TbTps2KonwnPoint data) {
        this.data = data;
    }
}

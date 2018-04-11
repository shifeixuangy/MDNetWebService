package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbTps2Project;

/**
 * Created by admin on 2016/4/22.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateTp2ProjectParameter {
    private int projID;
    private TbTps2Project data;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbTps2Project getData() {
        return data;
    }

    public void setData(TbTps2Project data) {
        this.data = data;
    }
}

package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbTps2ObserverStation;

/**
 * Created by admin on 2016/4/22.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateTps2ObserverStationParameter {
    private int projID;
    private TbTps2ObserverStation data;

    public TbTps2ObserverStation getData() {
        return data;
    }

    public void setData(TbTps2ObserverStation data) {
        this.data = data;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }
}

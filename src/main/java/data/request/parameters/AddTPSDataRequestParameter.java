package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddTPSDataRequestParameter {
    private int projID;
    private List<TPSDataWrapper>datas;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public List<TPSDataWrapper> getDatas() {
        return datas;
    }

    public void setDatas(List<TPSDataWrapper> datas) {
        this.datas = datas;
    }
}

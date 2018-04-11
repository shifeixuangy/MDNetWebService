package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/3/8.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetMonitorTypeListParameter {
    private int projID;
    private int status;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

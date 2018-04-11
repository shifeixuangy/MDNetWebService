package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/12/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DeleteEventRecordParameter {
    private int projID;
    private int eventRecordID;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getEventRecordID() {
        return eventRecordID;
    }

    public void setEventRecordID(int eventRecordID) {
        this.eventRecordID = eventRecordID;
    }
}

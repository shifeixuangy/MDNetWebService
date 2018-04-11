package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbEventRecord;

/**
 * Created by liudongdong on 2015/12/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateEventRecordParameter {
    private int projID;
    private TbEventRecord eventRecord;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbEventRecord getEventRecord() {
        return eventRecord;
    }

    public void setEventRecord(TbEventRecord eventRecord) {
        this.eventRecord = eventRecord;
    }
}

package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.Record;

/**
 * Created by liudongdong on 2015/11/2.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateRecordParameter {
    private int projID;
    private Record record;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}

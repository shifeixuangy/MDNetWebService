package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.sql.Timestamp;

/**
 * Created by admin on 2016/5/3.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetProjWarnCountAndLevelParameter {
    private int projID;
    private Timestamp begin;
    private Timestamp end;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}

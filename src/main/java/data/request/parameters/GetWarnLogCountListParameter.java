package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.ParameterValidator;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/5/7.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetWarnLogCountListParameter implements ParameterValidator {
    
    private int projID;
    
    private Timestamp startTime;
    
    private Timestamp endTime;

    @Override
    public boolean validate() {
        return (startTime!=null)&&(endTime!=null);
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}

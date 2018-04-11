package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/1/25.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetProjWarnNumberListResult {
    private int projID;
    private String projName;
    private int lastWeekWarnNumber;
    private int lastWeekNonDataWarnNumber;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public int getLastWeekWarnNumber() {
        return lastWeekWarnNumber;
    }

    public void setLastWeekWarnNumber(int lastWeekWarnNumber) {
        this.lastWeekWarnNumber = lastWeekWarnNumber;
    }

    public int getLastWeekNonDataWarnNumber() {
        return lastWeekNonDataWarnNumber;
    }

    public void setLastWeekNonDataWarnNumber(int lastWeekNonDataWarnNumber) {
        this.lastWeekNonDataWarnNumber = lastWeekNonDataWarnNumber;
    }
}

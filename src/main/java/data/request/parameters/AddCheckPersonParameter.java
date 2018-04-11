package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.CheckPerson;

/**
 * Created by liudongdong on 2015/11/4.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddCheckPersonParameter {
    private int projID;
    private CheckPerson checkPerson;


    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public CheckPerson getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(CheckPerson checkPerson) {
        this.checkPerson = checkPerson;
    }
}

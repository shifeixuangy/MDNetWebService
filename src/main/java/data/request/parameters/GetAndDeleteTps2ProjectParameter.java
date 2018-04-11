package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by admin on 2016/4/22.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetAndDeleteTps2ProjectParameter {
    private int projID;
    @JsonProperty("ID")
    private int id;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

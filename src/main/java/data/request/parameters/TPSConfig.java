package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TPSConfig {
    private String sensorName;
    private String note;

    public TPSConfig() {
    }

    public TPSConfig(String sensorName, String note) {
        this.sensorName = sensorName;
        this.note = note;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

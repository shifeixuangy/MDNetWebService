package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * Created by liudongdong on 2016/1/14.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class SensorImageWrapper {
    private int sensorID;
    private List<ImageWrapper>sensorImages;

    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    public List<ImageWrapper> getSensorImages() {
        return sensorImages;
    }

    public void setSensorImages(List<ImageWrapper> sensorImages) {
        this.sensorImages = sensorImages;
    }
}

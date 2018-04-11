package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbVolume;

/**
 * Created by liudongdong on 2015/9/8.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateVolumeParameter {
    private int projID;
    @JsonProperty("ID")
    private int id;
    private double stage;
    private double volume;

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

    public double getStage() {
        return stage;
    }

    public void setStage(double stage) {
        this.stage = stage;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }


    public TbVolume toTbVolume()
    {
        TbVolume tv=new TbVolume();
        tv.setId(this.id);
        tv.setStage(this.stage);
        tv.setVolume(this.volume);
        return  tv;
    }
}

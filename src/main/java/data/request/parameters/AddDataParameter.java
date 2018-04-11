package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbSensorAttri;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddDataParameter {
    private int projID;
    @NotNull
    private TbSensorAttri sensor;
    private List<ChannelDataWrapper> channelDatas;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbSensorAttri getSensor() {
        return sensor;
    }

    public void setSensor(TbSensorAttri sensor) {
        this.sensor = sensor;
    }

    public List<ChannelDataWrapper> getChannelDatas() {
        return channelDatas;
    }

    public void setChannelDatas(List<ChannelDataWrapper> channelDatas) {
        this.channelDatas = channelDatas;
    }
}

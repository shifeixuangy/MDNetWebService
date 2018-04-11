package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.ChannelDataKey;
import data.proj.TbChannelData;

import javax.validation.constraints.NotNull;

/**
 * Created by liudongdong on 2015/12/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddChannelDataParameter {
    private int projID;
    @NotNull
    private TbChannelData channelData;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbChannelData getChannelData() {
        return channelData;
    }

    public void setChannelData(TbChannelData channelData) {
        this.channelData = channelData;
    }

    public ChannelDataKey getChannelDataKey()
    {
        ChannelDataKey dk=new ChannelDataKey();
        dk.setProjID(this.projID);
        dk.setSensorID(this.channelData.getSensorId());
        dk.setChannelID(this.channelData.getChannelID());
        dk.setFileID(this.channelData.getFileID());
        dk.setDataTime(this.channelData.getTime());
        return dk;
    }
}

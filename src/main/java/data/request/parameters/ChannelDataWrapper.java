package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbChannel;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ChannelDataWrapper {
    @NotNull
    private TbChannel channel;
    private String channelData;
    private Timestamp time;
    private String fileID;

    public TbChannel getChannel() {
        return channel;
    }

    public void setChannel(TbChannel channel) {
        this.channel = channel;
    }

    public String getChannelData() {
        return channelData;
    }

    public void setChannelData(String channelData) {
        this.channelData = channelData;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }
}

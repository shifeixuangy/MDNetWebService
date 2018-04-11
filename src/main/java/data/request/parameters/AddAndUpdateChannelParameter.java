package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbChannel;

import javax.validation.constraints.NotNull;

/**
 * Created by liudongdong on 2015/12/6.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateChannelParameter {
    private int projID;
    @NotNull
    private TbChannel channel;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbChannel getChannel() {
        return channel;
    }

    public void setChannel(TbChannel channel) {
        this.channel = channel;
    }
}

package data.proj;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/12/18.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ShockExValues {
    private int rate;
    private String version;
    private int usedChannelCount;
    private int triggerChannel;
    private int dataBit;
    private  float triggerDelayTime;
    private float recordLength;
    private int dataCount;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getUsedChannelCount() {
        return usedChannelCount;
    }

    public void setUsedChannelCount(int usedChannelCount) {
        this.usedChannelCount = usedChannelCount;
    }

    public int getTriggerChannel() {
        return triggerChannel;
    }

    public void setTriggerChannel(int triggerChannel) {
        this.triggerChannel = triggerChannel;
    }

    public int getDataBit() {
        return dataBit;
    }

    public void setDataBit(int dataBit) {
        this.dataBit = dataBit;
    }

    public float getTriggerDelayTime() {
        return triggerDelayTime;
    }

    public void setTriggerDelayTime(float triggerDelayTime) {
        this.triggerDelayTime = triggerDelayTime;
    }

    public float getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(float recordLength) {
        this.recordLength = recordLength;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShockExValues that = (ShockExValues) o;

        if (rate != that.rate) return false;
        if (usedChannelCount != that.usedChannelCount) return false;
        if (triggerChannel != that.triggerChannel) return false;
        if (dataBit != that.dataBit) return false;
        if (Float.compare(that.triggerDelayTime, triggerDelayTime) != 0) return false;
        if (Float.compare(that.recordLength, recordLength) != 0) return false;
        if (dataCount != that.dataCount) return false;
        return !(version != null ? !version.equals(that.version) : that.version != null);

    }

    @Override
    public int hashCode() {
        int result = rate;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + usedChannelCount;
        result = 31 * result + triggerChannel;
        result = 31 * result + dataBit;
        result = 31 * result + (triggerDelayTime != +0.0f ? Float.floatToIntBits(triggerDelayTime) : 0);
        result = 31 * result + (recordLength != +0.0f ? Float.floatToIntBits(recordLength) : 0);
        result = 31 * result + dataCount;
        return result;
    }
}

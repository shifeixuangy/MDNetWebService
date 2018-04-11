package data.proj;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liudongdong on 2015/5/6.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class SensorWarnLogCount {
    private int sensorID;
    private int dealedCount;
    private int undealedCount;

    @Id
    @Basic
    @Column(name="SensorID")
    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    @Basic
    @Column(name="DealedCount")
    public int getDealedCount() {
        return dealedCount;
    }

    public void setDealedCount(int dealedCount) {
        this.dealedCount = dealedCount;
    }
    @Basic
    @Column(name="UndealedCount")
    public int getUndealedCount() {
        return undealedCount;
    }

    public void setUndealedCount(int undealedCount) {
        this.undealedCount = undealedCount;
    }
}

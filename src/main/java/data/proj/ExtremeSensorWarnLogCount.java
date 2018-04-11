package data.proj;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by liudongdong on 2015/5/6.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ExtremeSensorWarnLogCount implements Serializable {
    private int sensorID;
    private String warnChildType;
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

    @Id
    @Basic
    @Column(name="WarnChildType")
    public String getWarnChildType() {
        return warnChildType;
    }

    public void setWarnChildType(String warnChildType) {
        this.warnChildType = warnChildType;
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

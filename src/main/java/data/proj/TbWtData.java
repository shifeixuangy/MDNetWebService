package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_wt_data", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbWtData  implements SensorData{
    @JsonProperty("I")
    private int id;
    @JsonProperty("S")
    private int sensorId;
    @JsonProperty("T")
    private Timestamp time;
    @JsonProperty("ST")
    private double stage;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SensorID")
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @Basic
    @Column(name = "Time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "Stage")
    public double getStage() {
        return stage;
    }

    public void setStage(double stage) {
        this.stage = stage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbWtData)) return false;

        TbWtData tbWtData = (TbWtData) o;

        if (id != tbWtData.id) return false;
        if (sensorId != tbWtData.sensorId) return false;
        if (Double.compare(tbWtData.stage, stage) != 0) return false;
        if (time != null ? !time.equals(tbWtData.time) : tbWtData.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        temp = Double.doubleToLongBits(stage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

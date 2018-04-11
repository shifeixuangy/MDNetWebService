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
@Table(name = "tb_da_data_avg", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbDaDataAvg  implements SensorData{
    @JsonProperty("I")
    private int id;
    @JsonProperty("S")
    private int sensorId;
    @JsonProperty("T")
    private Timestamp time;
    @JsonProperty("A")
    private double angel;

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
    @Column(name = "Angel")
    public double getAngel() {
        return angel;
    }

    public void setAngel(double angel) {
        this.angel = angel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbDaDataAvg)) return false;

        TbDaDataAvg that = (TbDaDataAvg) o;

        if (Double.compare(that.angel, angel) != 0) return false;
        if (id != that.id) return false;
        if (sensorId != that.sensorId) return false;
        if (!time.equals(that.time)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorId;
        result = 31 * result + time.hashCode();
        temp = Double.doubleToLongBits(angel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

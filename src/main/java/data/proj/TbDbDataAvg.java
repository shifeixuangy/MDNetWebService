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
@Table(name = "tb_db_data_avg", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbDbDataAvg  implements SensorData{
    @JsonProperty("I")
    private int id;
    @JsonProperty("S")
    private int sensorId;
    @JsonProperty("T")
    private Timestamp time;
    @JsonProperty("L")
    private  double  lenth;
    @JsonProperty("A")
    private  double  angle;
    @JsonProperty("SA")
    private  double  safeHeight;

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
    @Column(name = "Lenth")
    public double getLenth() {
        return lenth;
    }

    public void setLenth(double lenth) {
        this.lenth = lenth;
    }

    @Basic
    @Column(name = "Angle")
    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Basic
    @Column(name = "SafeHeight")
    public double getSafeHeight() {
        return safeHeight;
    }

    public void setSafeHeight(double safeHeight) {
        this.safeHeight = safeHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbDbDataAvg)) return false;

        TbDbDataAvg that = (TbDbDataAvg) o;

        if (Double.compare(that.angle, angle) != 0) return false;
        if (id != that.id) return false;
        if (Double.compare(that.lenth, lenth) != 0) return false;
        if (Double.compare(that.safeHeight, safeHeight) != 0) return false;
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
        temp = Double.doubleToLongBits(lenth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(angle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(safeHeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

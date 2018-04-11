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
@Table(name = "tb_dm_data_avg", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbDmDataAvg  implements SensorData{
    @JsonProperty("I")
    private int id;
    @JsonProperty("S")
    private int sensorId;
    @JsonProperty("T")
    private Timestamp time;
    @JsonProperty("A")
    private double dispA;
    @JsonProperty("B")
    private double dispB;
    @JsonProperty("VA")
    private double velocityA;
    @JsonProperty("VB")
    private double velocityB;

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
    @Column(name = "Disp_a")
    public double getDispA() {
        return dispA;
    }

    public void setDispA(double dispA) {
        this.dispA = dispA;
    }

    @Basic
    @Column(name = "Disp_b")
    public double getDispB() {
        return dispB;
    }

    public void setDispB(double dispB) {
        this.dispB = dispB;
    }

    @Basic
    @Column(name = "Velocity_a")
    public double getVelocityA() {
        return velocityA;
    }

    public void setVelocityA(double velocityA) {
        this.velocityA = velocityA;
    }

    @Basic
    @Column(name = "Velocity_b")
    public double getVelocityB() {
        return velocityB;
    }

    public void setVelocityB(double velocityB) {
        this.velocityB = velocityB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbDmDataAvg)) return false;

        TbDmDataAvg that = (TbDmDataAvg) o;

        if (Double.compare(that.dispA, dispA) != 0) return false;
        if (Double.compare(that.dispB, dispB) != 0) return false;
        if (id != that.id) return false;
        if (sensorId != that.sensorId) return false;
        if (Double.compare(that.velocityA, velocityA) != 0) return false;
        if (Double.compare(that.velocityB, velocityB) != 0) return false;
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
        temp = Double.doubleToLongBits(dispA);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dispB);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocityA);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocityB);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

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
@Table(name = "tb_tps_data", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbTpsData  implements SensorData{
    @JsonProperty("I")
    private int id;
    @JsonProperty("S")
    private int sensorId;
    @JsonProperty("T")
    private Timestamp time;
    @JsonProperty("X")
    private double dispX;
    @JsonProperty("Y")
    private double dispY;
    @JsonProperty("H")
    private double dispH;
    @JsonProperty("VX")
    private double velocityX;
    @JsonProperty("VY")
    private double velocityY;
    @JsonProperty("VH")
    private double velocityH;
    @JsonProperty("A")
    private double azimuth;

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
    @Column(name = "Disp_X")
    public double getDispX() {
        return dispX;
    }

    public void setDispX(double dispX) {
        this.dispX = dispX;
    }

    @Basic
    @Column(name = "Disp_Y")
    public double getDispY() {
        return dispY;
    }

    public void setDispY(double dispY) {
        this.dispY = dispY;
    }

    @Basic
    @Column(name = "Disp_H")
    public double getDispH() {
        return dispH;
    }

    public void setDispH(double dispH) {
        this.dispH = dispH;
    }

    @Basic
    @Column(name = "Velocity_X")
    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    @Basic
    @Column(name = "Velocity_Y")
    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    @Basic
    @Column(name = "Velocity_H")
    public double getVelocityH() {
        return velocityH;
    }

    public void setVelocityH(double velocityH) {
        this.velocityH = velocityH;
    }

    @Basic
    @Column(name = "Azimuth")
    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbTpsData tbTpsData = (TbTpsData) o;

        if (id != tbTpsData.id) return false;
        if (sensorId != tbTpsData.sensorId) return false;
        if (Double.compare(tbTpsData.dispX, dispX) != 0) return false;
        if (Double.compare(tbTpsData.dispY, dispY) != 0) return false;
        if (Double.compare(tbTpsData.dispH, dispH) != 0) return false;
        if (Double.compare(tbTpsData.velocityX, velocityX) != 0) return false;
        if (Double.compare(tbTpsData.velocityY, velocityY) != 0) return false;
        if (Double.compare(tbTpsData.velocityH, velocityH) != 0) return false;
        if (Double.compare(tbTpsData.azimuth, azimuth) != 0) return false;
        return time.equals(tbTpsData.time);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorId;
        result = 31 * result + time.hashCode();
        temp = Double.doubleToLongBits(dispX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dispY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dispH);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocityX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocityY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocityH);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(azimuth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

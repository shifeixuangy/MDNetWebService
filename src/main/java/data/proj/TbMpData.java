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
@Table(name = "tb_mp_data", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbMpData  implements SensorData{
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
    @JsonProperty("AX")
    private double accelerX;
    @JsonProperty("AY")
    private double accelerY;
    @JsonProperty("AH")
    private double accelerH;
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
    @Column(name = "Acceler_X")
    public double getAccelerX() {
        return accelerX;
    }

    public void setAccelerX(double accelerX) {
        this.accelerX = accelerX;
    }

    @Basic
    @Column(name = "Acceler_Y")
    public double getAccelerY() {
        return accelerY;
    }

    public void setAccelerY(double accelerY) {
        this.accelerY = accelerY;
    }

    @Basic
    @Column(name = "Acceler_H")
    public double getAccelerH() {
        return accelerH;
    }

    public void setAccelerH(double accelerH) {
        this.accelerH = accelerH;
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
        if (!(o instanceof TbMpData)) return false;

        TbMpData tbMpData = (TbMpData) o;

        if (Double.compare(tbMpData.accelerH, accelerH) != 0) return false;
        if (Double.compare(tbMpData.accelerX, accelerX) != 0) return false;
        if (Double.compare(tbMpData.accelerY, accelerY) != 0) return false;
        if (Double.compare(tbMpData.azimuth, azimuth) != 0) return false;
        if (Double.compare(tbMpData.dispH, dispH) != 0) return false;
        if (Double.compare(tbMpData.dispX, dispX) != 0) return false;
        if (Double.compare(tbMpData.dispY, dispY) != 0) return false;
        if (id != tbMpData.id) return false;
        if (sensorId != tbMpData.sensorId) return false;
        if (Double.compare(tbMpData.velocityH, velocityH) != 0) return false;
        if (Double.compare(tbMpData.velocityX, velocityX) != 0) return false;
        if (Double.compare(tbMpData.velocityY, velocityY) != 0) return false;
        if (time != null ? !time.equals(tbMpData.time) : tbMpData.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
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
        temp = Double.doubleToLongBits(accelerX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(accelerY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(accelerH);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(azimuth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

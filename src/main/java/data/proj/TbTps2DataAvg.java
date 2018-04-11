package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_tps2_data_avg", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbTps2DataAvg implements SensorData {
    @JsonProperty("I")
    private int id;
    @JsonProperty("S")
    private int sensorId;
    @JsonProperty("T")
    private Timestamp time;
    @JsonProperty("X")
    private double disp_X;
    @JsonProperty("Y")
    private double disp_Y;
    @JsonProperty("H")
    private double disp_H;
    @JsonProperty("VX")
    private double velocity_X;
    @JsonProperty("VY")
    private double velocity_Y;
    @JsonProperty("VH")
    private double velocity_H;
    @JsonProperty("A")
    private double azimuth;
    @JsonProperty("HA")
    private double hzAngle;
    @JsonProperty("VA")
    private double vertAngle;
    @JsonProperty("D")
    private double distance;
    @JsonProperty("RX")
    private double rawDisp_X;
    @JsonProperty("RY")
    private double rawDisp_Y;
    @JsonProperty("RH")
    private double rawDisp_H;

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
    @Column(name="Disp_X")
    public double getDisp_X() {
        return disp_X;
    }

    public void setDisp_X(double disp_X) {
        this.disp_X = disp_X;
    }

    @Basic
    @Column(name="Disp_Y")
    public double getDisp_Y() {
        return disp_Y;
    }

    public void setDisp_Y(double disp_Y) {
        this.disp_Y = disp_Y;
    }

    @Basic
    @Column(name="Disp_H")
    public double getDisp_H() {
        return disp_H;
    }

    public void setDisp_H(double disp_H) {
        this.disp_H = disp_H;
    }

    @Basic
    @Column(name="SensorID")
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorID) {
        this.sensorId = sensorID;
    }

    @Basic
    @Column(name="Time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name="Velocity_X")
    public double getVelocity_X() {
        return velocity_X;
    }

    public void setVelocity_X(double velocity_X) {
        this.velocity_X = velocity_X;
    }

    @Basic
    @Column(name="Velocity_Y")
    public double getVelocity_Y() {
        return velocity_Y;
    }

    public void setVelocity_Y(double velocity_Y) {
        this.velocity_Y = velocity_Y;
    }

    @Basic
    @Column(name="Velocity_H")
    public double getVelocity_H() {
        return velocity_H;
    }

    public void setVelocity_H(double velocity_H) {
        this.velocity_H = velocity_H;
    }

    @Basic
    @Column(name="Azimuth")
    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    @Basic
    @Column(name="HzAngle")
    public double getHzAngle() {
        return hzAngle;
    }

    public void setHzAngle(double hzAngle) {
        this.hzAngle = hzAngle;
    }

    @Basic
    @Column(name="VertAngle")
    public double getVertAngle() {
        return vertAngle;
    }

    public void setVertAngle(double vertAngle) {
        this.vertAngle = vertAngle;
    }

    @Basic
    @Column(name="Distance")
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name="RawDisp_X")
    public double getRawDisp_X() {
        return rawDisp_X;
    }

    public void setRawDisp_X(double rawDisp_X) {
        this.rawDisp_X = rawDisp_X;
    }

    @Basic
    @Column(name="RawDisp_Y")
    public double getRawDisp_Y() {
        return rawDisp_Y;
    }

    public void setRawDisp_Y(double rawDisp_Y) {
        this.rawDisp_Y = rawDisp_Y;
    }

    @Basic
    @Column(name="RawDisp_H")
    public double getRawDisp_H() {
        return rawDisp_H;
    }

    public void setRawDisp_H(double rawDisp_H) {
        this.rawDisp_H = rawDisp_H;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbTps2DataAvg that = (TbTps2DataAvg) o;

        if (id != that.id) return false;
        if (sensorId != that.sensorId) return false;
        if (Double.compare(that.disp_X, disp_X) != 0) return false;
        if (Double.compare(that.disp_Y, disp_Y) != 0) return false;
        if (Double.compare(that.disp_H, disp_H) != 0) return false;
        if (Double.compare(that.velocity_X, velocity_X) != 0) return false;
        if (Double.compare(that.velocity_Y, velocity_Y) != 0) return false;
        if (Double.compare(that.velocity_H, velocity_H) != 0) return false;
        if (Double.compare(that.azimuth, azimuth) != 0) return false;
        if (Double.compare(that.hzAngle, hzAngle) != 0) return false;
        if (Double.compare(that.vertAngle, vertAngle) != 0) return false;
        if (Double.compare(that.distance, distance) != 0) return false;
        if (Double.compare(that.rawDisp_X, rawDisp_X) != 0) return false;
        if (Double.compare(that.rawDisp_Y, rawDisp_Y) != 0) return false;
        if (Double.compare(that.rawDisp_H, rawDisp_H) != 0) return false;
        return time.equals(that.time);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorId;
        result = 31 * result + time.hashCode();
        temp = Double.doubleToLongBits(disp_X);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(disp_Y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(disp_H);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocity_X);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocity_Y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(velocity_H);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(azimuth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hzAngle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(vertAngle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rawDisp_X);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rawDisp_Y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rawDisp_H);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

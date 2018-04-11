package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.SensorData;
import data.proj.TbMpData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 2016/5/5.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ExtremeMpData implements SensorData {
    private double disp;
    private double velocity;
    private double acceler;

    @Basic
    @Column(name = "Disp")
    public double getDisp() {
        return disp;
    }

    public void setDisp(double disp) {
        this.disp = disp;
    }

    @Basic
    @Column(name = "Velocity")
    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Basic
    @Column(name = "Acceler")
    public double getAcceler() {
        return acceler;
    }

    public void setAcceler(double acceler) {
        this.acceler = acceler;
    }

    @JsonProperty("ID")
    private int id;
    @JsonProperty("SensorID")
    private int sensorId;
    private Timestamp time;
    @JsonProperty("Disp_X")
    private double dispX;
    @JsonProperty("Disp_Y")
    private double dispY;
    @JsonProperty("Disp_H")
    private double dispH;
    @JsonProperty("Velocity_X")
    private double velocityX;
    @JsonProperty("Velocity_Y")
    private double velocityY;
    @JsonProperty("Velocity_H")
    private double velocityH;
    @JsonProperty("Acceler_X")
    private double accelerX;
    @JsonProperty("Acceler_Y")
    private double accelerY;
    @JsonProperty("Acceler_H")
    private double accelerH;
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
}

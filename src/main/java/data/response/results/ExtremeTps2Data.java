package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.SensorData;
import data.proj.TbTps2Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 2016/5/5.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ExtremeTps2Data implements SensorData {
    private double disp;
    private double velocity;
    private double rawDisp;

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
    @Column(name = "RawDisp")
    public double getRawDisp() {
        return rawDisp;
    }

    public void setRawDisp(double rawDisp) {
        this.rawDisp = rawDisp;
    }

    @JsonProperty("ID")
    private int id;
    @JsonProperty("SensorID")
    private int sensorId;
    private Timestamp time;
    @JsonProperty("Disp_X")
    private double disp_X;
    @JsonProperty("Disp_Y")
    private double disp_Y;
    @JsonProperty("Disp_H")
    private double disp_H;
    @JsonProperty("Velocity_X")
    private double velocity_X;
    @JsonProperty("Velocity_Y")
    private double velocity_Y;
    @JsonProperty("Velocity_H")
    private double velocity_H;
    private double azimuth;
    private double hzAngle;
    private double vertAngle;
    private double distance;
    @JsonProperty("RawDisp_X")
    private double rawDisp_X;
    @JsonProperty("RawDisp_Y")
    private double rawDisp_Y;
    @JsonProperty("RawDisp_H")
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
}

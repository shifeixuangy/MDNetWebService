package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.SensorData;
import data.proj.TbDmData;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 2016/5/5.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ExtremeDmData implements SensorData {
    private double disp;
    private double velocity;

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

    @JsonProperty("ID")
    private int id;
    @JsonProperty("SensorID")
    private int sensorId;
    private Timestamp time;
    @JsonProperty("Disp_a")
    private double dispA;
    @JsonProperty("Disp_b")
    private double dispB;
    @JsonProperty("Velocity_a")
    private double velocityA;
    @JsonProperty("Velocity_b")
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
}

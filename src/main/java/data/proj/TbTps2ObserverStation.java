package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_tps2_observer_station", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbTps2ObserverStation{
    @JsonProperty("ID")
    private int id;
    private String name;
    @JsonProperty("Disp_X")
    private double disp_X;
    @JsonProperty("Disp_Y")
    private double disp_Y;
    @JsonProperty("Disp_H")
    private double disp_H;
    private String lookName;
    @JsonProperty("LookDisp_X")
    private double lookDisp_X;
    @JsonProperty("LookDisp_Y")
    private double lookDisp_Y;
    @JsonProperty("LookDisp_H")
    private double lookDisp_H;
    private double machineHeight;
    private double glassHeight;
    private double lookAzimuth;

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
    @Column(name="Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name="LookName")
    public String getLookName() {
        return lookName;
    }

    public void setLookName(String lookName) {
        this.lookName = lookName;
    }

    @Basic
    @Column(name="LookDisp_X")
    public double getLookDisp_X() {
        return lookDisp_X;
    }

    public void setLookDisp_X(double lookDisp_X) {
        this.lookDisp_X = lookDisp_X;
    }

    @Basic
    @Column(name="LookDisp_Y")
    public double getLookDisp_Y() {
        return lookDisp_Y;
    }

    public void setLookDisp_Y(double lookDisp_Y) {
        this.lookDisp_Y = lookDisp_Y;
    }

    @Basic
    @Column(name="LookDisp_H")
    public double getLookDisp_H() {
        return lookDisp_H;
    }

    public void setLookDisp_H(double lookDisp_H) {
        this.lookDisp_H = lookDisp_H;
    }

    @Basic
    @Column(name="MachineHeight")
    public double getMachineHeight() {
        return machineHeight;
    }

    public void setMachineHeight(double machineHeight) {
        this.machineHeight = machineHeight;
    }

    @Basic
    @Column(name="GlassHeight")
    public double getGlassHeight() {
        return glassHeight;
    }

    public void setGlassHeight(double glassHeight) {
        this.glassHeight = glassHeight;
    }

    @Basic
    @Column(name="LookAzimuth")
    public double getLookAzimuth() {
        return lookAzimuth;
    }

    public void setLookAzimuth(double lookAzimuth) {
        this.lookAzimuth = lookAzimuth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbTps2ObserverStation that = (TbTps2ObserverStation) o;

        if (Double.compare(that.disp_H, disp_H) != 0) return false;
        if (Double.compare(that.disp_X, disp_X) != 0) return false;
        if (Double.compare(that.disp_Y, disp_Y) != 0) return false;
        if (Double.compare(that.glassHeight, glassHeight) != 0) return false;
        if (id != that.id) return false;
        if (Double.compare(that.lookAzimuth, lookAzimuth) != 0) return false;
        if (Double.compare(that.lookDisp_H, lookDisp_H) != 0) return false;
        if (Double.compare(that.lookDisp_X, lookDisp_X) != 0) return false;
        if (Double.compare(that.lookDisp_Y, lookDisp_Y) != 0) return false;
        if (Double.compare(that.machineHeight, machineHeight) != 0) return false;
        if (lookName != null ? !lookName.equals(that.lookName) : that.lookName != null) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(disp_X);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(disp_Y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(disp_H);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (lookName != null ? lookName.hashCode() : 0);
        temp = Double.doubleToLongBits(lookDisp_X);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lookDisp_Y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lookDisp_H);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(machineHeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(glassHeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lookAzimuth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

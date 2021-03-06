package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_warn_value", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbWarnValue {
    @JsonProperty("ID")
    private int id;
    private int sensorType;
    private String warnType;
    private short warnLevel;
    private double value;
    @JsonProperty("IsValid")
    private boolean isValid;

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
    @Column(name = "SensorType")
    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    @Basic
    @Column(name = "WarnType")
    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    @Basic
    @Column(name = "WarnLevel")
    public short getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(short warnLevel) {
        this.warnLevel = warnLevel;
    }

    @Basic
    @Column(name = "Value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "IsValid")
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbWarnValue)) return false;

        TbWarnValue that = (TbWarnValue) o;

        if (id != that.id) return false;
        if (isValid != that.isValid) return false;
        if (sensorType != that.sensorType) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (warnLevel != that.warnLevel) return false;
        if (warnType != null ? !warnType.equals(that.warnType) : that.warnType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorType;
        result = 31 * result + (warnType != null ? warnType.hashCode() : 0);
        result = 31 * result + (int) warnLevel;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isValid ? 1 : 0);
        return result;
    }
}

package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_warn_revise", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbWarnRevise {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("SensorID")
    private int sensorId;
    private String warnChildType;
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
    @Column(name = "SensorID")
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @Basic
    @Column(name = "WarnChildType")
    public String getWarnChildType() {
        return warnChildType;
    }

    public void setWarnChildType(String warnChildType) {
        this.warnChildType = warnChildType;
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
        if (!(o instanceof TbWarnRevise)) return false;

        TbWarnRevise that = (TbWarnRevise) o;

        if (id != that.id) return false;
        if (isValid != that.isValid) return false;
        if (sensorId != that.sensorId) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (warnChildType != null ? !warnChildType.equals(that.warnChildType) : that.warnChildType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorId;
        result = 31 * result + (warnChildType != null ? warnChildType.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isValid ? 1 : 0);
        return result;
    }
}

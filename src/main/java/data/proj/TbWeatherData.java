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
@Table(name = "tb_weather_data", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbWeatherData  implements SensorData{
    @JsonProperty("ID")
    private int id;
    @JsonProperty("SensorID")
    private int sensorId;
    private Timestamp time;
    private double value11;
    private double value12;
    private double value13;
    private double value21;
    private double value22;
    private double value23;
    private double value31;
    private double value32;
    private double value33;
    private double value41;
    private double value42;
    private double value43;
    private double value51;
    private double value52;
    private double value53;
    private double value61;
    private double value62;
    private double value63;
    private double temperature;
    private double powerVoltage;
    private double deviceVoltage;
    private double airPressure;

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
    @Column(name = "Value1_1")
    public double getValue11() {
        return value11;
    }

    public void setValue11(double value11) {
        this.value11 = value11;
    }

    @Basic
    @Column(name = "Value1_2")
    public double getValue12() {
        return value12;
    }

    public void setValue12(double value12) {
        this.value12 = value12;
    }

    @Basic
    @Column(name = "Value1_3")
    public double getValue13() {
        return value13;
    }

    public void setValue13(double value13) {
        this.value13 = value13;
    }

    @Basic
    @Column(name = "Value2_1")
    public double getValue21() {
        return value21;
    }

    public void setValue21(double value21) {
        this.value21 = value21;
    }

    @Basic
    @Column(name = "Value2_2")
    public double getValue22() {
        return value22;
    }

    public void setValue22(double value22) {
        this.value22 = value22;
    }

    @Basic
    @Column(name = "Value2_3")
    public double getValue23() {
        return value23;
    }

    public void setValue23(double value23) {
        this.value23 = value23;
    }

    @Basic
    @Column(name = "Value3_1")
    public double getValue31() {
        return value31;
    }

    public void setValue31(double value31) {
        this.value31 = value31;
    }

    @Basic
    @Column(name = "Value3_2")
    public double getValue32() {
        return value32;
    }

    public void setValue32(double value32) {
        this.value32 = value32;
    }

    @Basic
    @Column(name = "Value3_3")
    public double getValue33() {
        return value33;
    }

    public void setValue33(double value33) {
        this.value33 = value33;
    }

    @Basic
    @Column(name = "Value4_1")
    public double getValue41() {
        return value41;
    }

    public void setValue41(double value41) {
        this.value41 = value41;
    }

    @Basic
    @Column(name = "Value4_2")
    public double getValue42() {
        return value42;
    }

    public void setValue42(double value42) {
        this.value42 = value42;
    }

    @Basic
    @Column(name = "Value4_3")
    public double getValue43() {
        return value43;
    }

    public void setValue43(double value43) {
        this.value43 = value43;
    }

    @Basic
    @Column(name = "Value5_1")
    public double getValue51() {
        return value51;
    }

    public void setValue51(double value51) {
        this.value51 = value51;
    }

    @Basic
    @Column(name = "Value5_2")
    public double getValue52() {
        return value52;
    }

    public void setValue52(double value52) {
        this.value52 = value52;
    }

    @Basic
    @Column(name = "Value5_3")
    public double getValue53() {
        return value53;
    }

    public void setValue53(double value53) {
        this.value53 = value53;
    }

    @Basic
    @Column(name = "Value6_1")
    public double getValue61() {
        return value61;
    }

    public void setValue61(double value61) {
        this.value61 = value61;
    }

    @Basic
    @Column(name = "Value6_2")
    public double getValue62() {
        return value62;
    }

    public void setValue62(double value62) {
        this.value62 = value62;
    }

    @Basic
    @Column(name = "Value6_3")
    public double getValue63() {
        return value63;
    }

    public void setValue63(double value63) {
        this.value63 = value63;
    }

    @Basic
    @Column(name = "Temperature")
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Basic
    @Column(name = "PowerVoltage")
    public double getPowerVoltage() {
        return powerVoltage;
    }

    public void setPowerVoltage(double powerVoltage) {
        this.powerVoltage = powerVoltage;
    }

    @Basic
    @Column(name = "DeviceVoltage")
    public double getDeviceVoltage() {
        return deviceVoltage;
    }

    public void setDeviceVoltage(double deviceVoltage) {
        this.deviceVoltage = deviceVoltage;
    }

    @Basic
    @Column(name = "AirPressure")
    public double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbWeatherData)) return false;

        TbWeatherData that = (TbWeatherData) o;

        if (Double.compare(that.airPressure, airPressure) != 0) return false;
        if (Double.compare(that.deviceVoltage, deviceVoltage) != 0) return false;
        if (id != that.id) return false;
        if (Double.compare(that.powerVoltage, powerVoltage) != 0) return false;
        if (sensorId != that.sensorId) return false;
        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (Double.compare(that.value11, value11) != 0) return false;
        if (Double.compare(that.value12, value12) != 0) return false;
        if (Double.compare(that.value13, value13) != 0) return false;
        if (Double.compare(that.value21, value21) != 0) return false;
        if (Double.compare(that.value22, value22) != 0) return false;
        if (Double.compare(that.value23, value23) != 0) return false;
        if (Double.compare(that.value31, value31) != 0) return false;
        if (Double.compare(that.value32, value32) != 0) return false;
        if (Double.compare(that.value33, value33) != 0) return false;
        if (Double.compare(that.value41, value41) != 0) return false;
        if (Double.compare(that.value42, value42) != 0) return false;
        if (Double.compare(that.value43, value43) != 0) return false;
        if (Double.compare(that.value51, value51) != 0) return false;
        if (Double.compare(that.value52, value52) != 0) return false;
        if (Double.compare(that.value53, value53) != 0) return false;
        if (Double.compare(that.value61, value61) != 0) return false;
        if (Double.compare(that.value62, value62) != 0) return false;
        if (Double.compare(that.value63, value63) != 0) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + sensorId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        temp = Double.doubleToLongBits(value11);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value41);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value42);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value43);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value51);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value52);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value53);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value61);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value62);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value63);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(powerVoltage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(deviceVoltage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(airPressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

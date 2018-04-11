package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/11/2.
 */
@Entity
@Table(name = "`check`", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class Check {
    @JsonProperty("ID")
    private int id;
    private Timestamp time;
    private String weather;
    private float temperature;
    private int uploadUserID;
    private String remark;
    private String title;

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
    @Column(name = "`Time`")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    @Basic
    @Column(name = "Weather")
    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    @Basic
    @Column(name = "Temperature")
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Basic
    @Column(name = "UploadUserID")
    public int getUploadUserID() {
        return uploadUserID;
    }

    public void setUploadUserID(int uploadUserID) {
        this.uploadUserID = uploadUserID;
    }
    @Basic
    @Column(name = "Remark",length = 4000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (id != check.id) return false;
        if (Float.compare(check.temperature, temperature) != 0) return false;
        if (uploadUserID != check.uploadUserID) return false;
        if (!time.equals(check.time)) return false;
        if (weather != null ? !weather.equals(check.weather) : check.weather != null) return false;
        if (remark != null ? !remark.equals(check.remark) : check.remark != null) return false;
        return !(title != null ? !title.equals(check.title) : check.title != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + time.hashCode();
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (temperature != +0.0f ? Float.floatToIntBits(temperature) : 0);
        result = 31 * result + uploadUserID;
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}

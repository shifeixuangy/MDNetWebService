package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_tps2_project", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbTps2Project{
    @JsonProperty("ID")
    private int id;
    private String projName;
    private String operator;
    private Timestamp projTime;
    private String weather;
    private String projInfo;

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
    @Column(name = "ProjName")
    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    @Basic
    @Column(name = "Operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "ProjTime")
    public Timestamp getProjTime() {
        return projTime;
    }

    public void setProjTime(Timestamp projTime) {
        this.projTime = projTime;
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
    @Column(name = "ProjInfo")
    public String getProjInfo() {
        return projInfo;
    }

    public void setProjInfo(String projInfo) {
        this.projInfo = projInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbTps2Project that = (TbTps2Project) o;

        if (id != that.id) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (projInfo != null ? !projInfo.equals(that.projInfo) : that.projInfo != null) return false;
        if (projName != null ? !projName.equals(that.projName) : that.projName != null) return false;
        if (projTime != null ? !projTime.equals(that.projTime) : that.projTime != null) return false;
        if (weather != null ? !weather.equals(that.weather) : that.weather != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (projName != null ? projName.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (projTime != null ? projTime.hashCode() : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (projInfo != null ? projInfo.hashCode() : 0);
        return result;
    }
}

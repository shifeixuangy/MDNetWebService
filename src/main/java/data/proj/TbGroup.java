package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_group", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbGroup {
    @JsonProperty("ID")
    private int id;
    private int sensorType;
    private String name;
    private String alias;
    private String note;
    private String exValues;

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
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Basic
    @Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "ExValues")
    public String getExValues() {
        return exValues;
    }

    public void setExValues(String exValues) {
        this.exValues = exValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbGroup tbGroup = (TbGroup) o;

        if (id != tbGroup.id) return false;
        if (sensorType != tbGroup.sensorType) return false;
        if (!name.equals(tbGroup.name)) return false;
        if (alias != null ? !alias.equals(tbGroup.alias) : tbGroup.alias != null) return false;
        if (note != null ? !note.equals(tbGroup.note) : tbGroup.note != null) return false;
        return !(exValues != null ? !exValues.equals(tbGroup.exValues) : tbGroup.exValues != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sensorType;
        result = 31 * result + name.hashCode();
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (exValues != null ? exValues.hashCode() : 0);
        return result;
    }
}

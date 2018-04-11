package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/17.
 */
@Entity
@Table(name = "tb_proj_power", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbProjPower {
    @JsonProperty("ID")
    private int id;
    private String powerName;
    private String note;

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
    @Column(name = "PowerName")
    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    @Basic
    @Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbProjPower that = (TbProjPower) o;

        if (id != that.id) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (powerName != null ? !powerName.equals(that.powerName) : that.powerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (powerName != null ? powerName.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}

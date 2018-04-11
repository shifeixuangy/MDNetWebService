package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@Entity
@Table(name = "tb_tps2_konwn_point", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbTps2KonwnPoint {
    @JsonProperty("ID")
    private int id;
    private String name;
    @JsonProperty("Disp_X")
    private double disp_X;
    @JsonProperty("Disp_Y")
    private double disp_Y;
    @JsonProperty("Disp_H")
    private double disp_H;
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
    @Column(name="Note")
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

        TbTps2KonwnPoint that = (TbTps2KonwnPoint) o;

        if (Double.compare(that.disp_H, disp_H) != 0) return false;
        if (Double.compare(that.disp_X, disp_X) != 0) return false;
        if (Double.compare(that.disp_Y, disp_Y) != 0) return false;
        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

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
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}

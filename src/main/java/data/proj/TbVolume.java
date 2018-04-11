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
@Table(name = "tb_volume", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbVolume{
    @JsonProperty("ID")
    private int id;
    private double stage;
    private double volume;

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
    @Column(name = "Stage")
    public double getStage() {
        return stage;
    }

    public void setStage(double stage) {
        this.stage = stage;
    }
    @Basic
    @Column(name = "Volume")
    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbVolume)) return false;

        TbVolume tbVolume = (TbVolume) o;

        if (id != tbVolume.id) return false;
        if (Double.compare(tbVolume.stage, stage) != 0) return false;
        if (Double.compare(tbVolume.volume, volume) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(stage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

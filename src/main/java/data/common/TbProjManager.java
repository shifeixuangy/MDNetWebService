package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/17.
 */
@Entity
@Table(name = "tb_proj_manager", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbProjManager {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("UID")
    private int uid;
    @JsonProperty("ProjID")
    private int projId;
    @JsonProperty("PowerID")
    private int powerId;

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
    @Column(name = "UID")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "ProjID")
    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    @Basic
    @Column(name = "PowerID")
    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbProjManager that = (TbProjManager) o;

        if (id != that.id) return false;
        if (powerId != that.powerId) return false;
        if (projId != that.projId) return false;
        if (uid != that.uid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + uid;
        result = 31 * result + projId;
        result = 31 * result + powerId;
        return result;
    }
}

package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/17.
 */
@Entity
@Table(name = "tb_region_manager", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbRegionManager {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("UID")
    private int uid;
    @JsonProperty("RegionID")
    private int regionId;
    @JsonProperty("IsAdmin")
    private boolean isAdmin;

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
    @Column(name = "RegionID")
    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "IsAdmin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbRegionManager that = (TbRegionManager) o;

        if (id != that.id) return false;
        if (isAdmin != that.isAdmin) return false;
        if (regionId != that.regionId) return false;
        if (uid != that.uid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + uid;
        result = 31 * result + regionId;
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }
}

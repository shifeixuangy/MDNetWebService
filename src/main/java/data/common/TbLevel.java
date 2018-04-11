package data.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dataDAO.CommonQueryHelper;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/17.
 */
@Entity
@Table(name = "tb_level", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbLevel {
    @JsonProperty("ID")
    private int id;
    private int fatherLevelID;
    private String levelName;
    @JsonIgnore
    private int levelDepth;

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
    @Column(name = "FatherLevelID")
    public int getFatherLevelID() {
        return fatherLevelID;
    }

    public void setFatherLevelID(int fatherLevelID) {
        this.fatherLevelID = fatherLevelID;
    }

    @Basic
    @Column(name = "LevelName")
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Basic
    @Column(name = "LevelDepth")
    public int getLevelDepth() {
        return levelDepth;
    }

    public void setLevelDepth(int levelDepth) {
        this.levelDepth = levelDepth;
    }

    public void autoSetLevelDepth()
    {
        if(this.fatherLevelID==0)
            this.setLevelDepth(1);
        else
        {
            TbLevel fatherLevel= CommonQueryHelper.getLevels(this.getFatherLevelID()).get(0);
            this.setLevelDepth(fatherLevel.getLevelDepth()+1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLevel tbLevel = (TbLevel) o;

        if (id != tbLevel.id) return false;
        if (fatherLevelID != tbLevel.fatherLevelID) return false;
        if (levelDepth != tbLevel.levelDepth) return false;
        return levelName.equals(tbLevel.levelName);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fatherLevelID;
        result = 31 * result + levelName.hashCode();
        result = 31 * result + levelDepth;
        return result;
    }
}

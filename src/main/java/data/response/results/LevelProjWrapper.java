package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * Created by liudongdong on 2016/3/22.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class LevelProjWrapper {
    private int levelID;
    private int fatherLevelID;
    private String levelName;
    private List<GetProjListResult>levelProjs;
    private List<LevelProjWrapper>subLevels;

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public int getFatherLevelID() {
        return fatherLevelID;
    }

    public void setFatherLevelID(int fatherLevelID) {
        this.fatherLevelID = fatherLevelID;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public List<GetProjListResult> getLevelProjs() {
        return levelProjs;
    }

    public void setLevelProjs(List<GetProjListResult> levelProjs) {
        this.levelProjs = levelProjs;
    }

    public List<LevelProjWrapper> getSubLevels() {
        return subLevels;
    }

    public void setSubLevels(List<LevelProjWrapper> subLevels) {
        this.subLevels = subLevels;
    }
}

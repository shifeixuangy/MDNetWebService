package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by admin on 2016/5/3.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ProjWarnCountAndLevelResult {
    private int projID;
    private int unHandlerCount;
    private int warnLevel;

    @Id
    @Basic
    @Column(name="ProjID")
    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    @Basic
    @Column(name="UnHandlerCount")
    public int getUnHandlerCount() {
        return unHandlerCount;
    }

    public void setUnHandlerCount(int unHandlerCount) {
        this.unHandlerCount = unHandlerCount;
    }

    @Basic
    @Column(name="WarnLevel")
    public int getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }
}

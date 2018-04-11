package data.proj;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by admin on 2016/7/5.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class StExValues {
    private String position;
    private float holeStage;
    private float installStage;
    private float theoryValue;
    private float transverseValue;
    private float longitudinalValue;
    private int transGroupID;
    private int longGroupID;
    private String picName;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getHoleStage() {
        return holeStage;
    }

    public void setHoleStage(float holeStage) {
        this.holeStage = holeStage;
    }

    public float getInstallStage() {
        return installStage;
    }

    public void setInstallStage(float installStage) {
        this.installStage = installStage;
    }

    public float getTheoryValue() {
        return theoryValue;
    }

    public void setTheoryValue(float theoryValue) {
        this.theoryValue = theoryValue;
    }

    public float getTransverseValue() {
        return transverseValue;
    }

    public void setTransverseValue(float transverseValue) {
        this.transverseValue = transverseValue;
    }

    public float getLongitudinalValue() {
        return longitudinalValue;
    }

    public void setLongitudinalValue(float longitudinalValue) {
        this.longitudinalValue = longitudinalValue;
    }

    public int getTransGroupID() {
        return transGroupID;
    }

    public void setTransGroupID(int transGroupID) {
        this.transGroupID = transGroupID;
    }

    public int getLongGroupID() {
        return longGroupID;
    }

    public void setLongGroupID(int longGroupID) {
        this.longGroupID = longGroupID;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}

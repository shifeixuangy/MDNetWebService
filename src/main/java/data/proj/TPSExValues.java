package data.proj;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TPSExValues {
    private int targetType;
    private double initX;
    private double initY;
    private double initH;

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public double getInitX() {
        return initX;
    }

    public void setInitX(double initX) {
        this.initX = initX;
    }

    public double getInitY() {
        return initY;
    }

    public void setInitY(double initY) {
        this.initY = initY;
    }

    public double getInitH() {
        return initH;
    }

    public void setInitH(double initH) {
        this.initH = initH;
    }
}

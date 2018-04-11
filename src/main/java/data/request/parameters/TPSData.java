package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TPSData {
    private Timestamp time;
    private double x;
    private double y;
    private  double h;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public static class TPSDataComparator implements Comparator<TPSData>
    {
        @Override
        public int compare(TPSData o1, TPSData o2) {
            return o1.getTime().compareTo(o2.getTime());
        }
    }
}

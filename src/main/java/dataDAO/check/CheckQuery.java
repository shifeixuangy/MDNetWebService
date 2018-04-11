package dataDAO.check;

import data.proj.Check;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class CheckQuery extends ObjectsQueryBase<Check> {
    @Override
    public String getFrom() {
        return " from data.proj.Check ";
    }
}

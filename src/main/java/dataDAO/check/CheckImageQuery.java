package dataDAO.check;

import data.proj.CheckImage;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class CheckImageQuery extends ObjectsQueryBase<CheckImage> {
    @Override
    public String getFrom() {
        return " from data.proj.CheckImage ";
    }
}

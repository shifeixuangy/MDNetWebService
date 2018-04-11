package dataDAO.check;

import data.proj.CheckPerson;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class CheckPersonQuery extends ObjectsQueryBase<CheckPerson> {
    @Override
    public String getFrom() {
        return " from data.proj.CheckPerson  ";
    }
}

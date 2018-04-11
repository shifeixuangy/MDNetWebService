package dataDAO.proj.impl;

import data.proj.TbWarnPerson;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class WarnPersonQuery extends ObjectsQueryBase<TbWarnPerson> {
    @Override
    public String getFrom() {
        return " from data.proj.TbWarnPerson";
    }
}

package dataDAO.proj.impl;

import data.proj.TbWarnValue;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/5.
 */
public class WarnValueQuery extends ObjectsQueryBase<TbWarnValue> {
    @Override
    public String getFrom() {
        return " from data.proj.TbWarnValue  ";
    }
}

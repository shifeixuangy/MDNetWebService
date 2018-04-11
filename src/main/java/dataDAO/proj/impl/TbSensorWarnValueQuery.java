package dataDAO.proj.impl;

import data.proj.TbSensorWarnValue;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/11/13.
 */
public class TbSensorWarnValueQuery extends ObjectsQueryBase<TbSensorWarnValue> {
    @Override
    public String getFrom() {
        return " from data.proj.TbSensorWarnValue  ";
    }
}

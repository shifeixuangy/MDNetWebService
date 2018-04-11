package dataDAO.proj.impl;

import data.proj.TbSensorAttri;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/4.
 */
public class TbSensorAttriQuery extends ObjectsQueryBase<TbSensorAttri> {
    @Override
    public String getFrom() {
        return "from data.proj.TbSensorAttri";
    }
}

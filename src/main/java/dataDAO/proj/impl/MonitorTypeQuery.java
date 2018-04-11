package dataDAO.proj.impl;

import data.proj.TbMonitorType;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class MonitorTypeQuery extends ObjectsQueryBase<TbMonitorType> {
    @Override
    public String getFrom() {
        return " from  data.proj.TbMonitorType";
    }
}

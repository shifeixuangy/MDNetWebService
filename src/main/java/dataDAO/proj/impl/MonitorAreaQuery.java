package dataDAO.proj.impl;

import data.proj.TbMonitorArea;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class MonitorAreaQuery  extends ObjectsQueryBase<TbMonitorArea>{
    @Override
    public String getFrom() {
        return "from data.proj.TbMonitorArea";
    }
}

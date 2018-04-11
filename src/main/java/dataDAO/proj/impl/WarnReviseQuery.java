package dataDAO.proj.impl;

import data.proj.TbWarnRevise;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/6.
 */
public class WarnReviseQuery extends ObjectsQueryBase<TbWarnRevise> {
    @Override
    public String getFrom() {
        return "from data.proj.TbWarnRevise";
    }
}

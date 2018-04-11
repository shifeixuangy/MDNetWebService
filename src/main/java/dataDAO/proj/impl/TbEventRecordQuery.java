package dataDAO.proj.impl;

import data.proj.TbEventRecord;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/12/4.
 */
public class TbEventRecordQuery extends ObjectsQueryBase<TbEventRecord> {
    @Override
    public String getFrom() {
        return " from data.proj.TbEventRecord  ";
    }
}

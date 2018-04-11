package dataDAO.check;

import data.proj.Record;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class RecordQuery extends ObjectsQueryBase<Record> {
    @Override
    public String getFrom() {
        return " from data.proj.Record  ";
    }
}

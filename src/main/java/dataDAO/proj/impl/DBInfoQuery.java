package dataDAO.proj.impl;

import data.proj.DBInfo;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2016/2/25.
 */
public class DBInfoQuery extends ObjectsQueryBase<DBInfo> {
    @Override
    public String getFrom() {
        return " from data.proj.DBInfo ";
    }
}

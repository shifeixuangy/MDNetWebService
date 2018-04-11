package dataDAO.impl;

import dataDAO.interfaces.CommonQuery;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public abstract class ObjectsQueryBase<T> implements ObjectsQuery<T> {
    protected CommonQuery<T>queryBase=new CommonQueryImpl<T>();

    @Override
    public List<T> query(String where, int projID) {
        return queryBase.query(getFrom(),where,projID);
    }

    public abstract String getFrom();

}

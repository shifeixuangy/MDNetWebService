package dataDAO.impl;

import data.common.TbLog;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbLogQuery extends ObjectsQueryBase<TbLog> {
    public TbLogQuery()
    {}

    public TbLogQuery(int pageIndex,int pageSize)
    {
        queryBase=new CommonQueryImpl<>(pageIndex,pageSize);
    }

    @Override
    public String getFrom() {
        return "from data.common.TbLog   ";
    }
}

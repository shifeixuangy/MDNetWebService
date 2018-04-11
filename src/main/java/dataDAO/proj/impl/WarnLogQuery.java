package dataDAO.proj.impl;

import data.proj.TbWarnLog;
import dataDAO.impl.CommonQueryImpl;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/6.
 */
public class WarnLogQuery extends ObjectsQueryBase<TbWarnLog> {

    public WarnLogQuery()
    {}
    public WarnLogQuery(int pageIndex,int pageSize)
    {
        queryBase=new CommonQueryImpl<>(pageIndex,pageSize);
    }

    @Override
    public String getFrom() {
        return "from data.proj.TbWarnLog";
    }
}

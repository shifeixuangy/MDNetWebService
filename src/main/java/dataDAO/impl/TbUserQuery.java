package dataDAO.impl;

import commonUtil.CommonVariable;
import data.common.TbUser;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbUserQuery extends ObjectsQueryBase<TbUser> {
    @Override
    public String getFrom() {
        return "from data.common.TbUser  ";
    }
}

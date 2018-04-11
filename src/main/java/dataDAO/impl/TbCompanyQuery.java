package dataDAO.impl;

import data.common.TbCompany;

/**
 * Created by liudongdong on 2016/3/15.
 */
public class TbCompanyQuery extends ObjectsQueryBase<TbCompany> {
    @Override
    public String getFrom() {
        return " from data.common.TbCompany ";
    }
}

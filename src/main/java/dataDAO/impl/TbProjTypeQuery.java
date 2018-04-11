package dataDAO.impl;

import data.common.TbProjType;

/**
 * Created by admin on 2016/6/12.
 */
public class TbProjTypeQuery extends ObjectsQueryBase<TbProjType> {
    @Override
    public String getFrom() {
        return " from data.common.TbProjType ";
    }
}

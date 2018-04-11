package dataDAO.impl;

import data.common.TbLevel;

/**
 * Created by liudongdong on 2016/3/22.
 */
public class TbLevelQuery extends ObjectsQueryBase<TbLevel> {
    @Override
    public String getFrom() {
        return " from data.common.TbLevel ";
    }
}

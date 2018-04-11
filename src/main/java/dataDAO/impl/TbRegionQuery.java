package dataDAO.impl;

import data.common.TbRegion;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbRegionQuery extends ObjectsQueryBase<TbRegion>  {
    @Override
    public String getFrom() {
        return "from data.common.TbRegion  ";
    }
}

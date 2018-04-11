package dataDAO.impl;

import data.common.TbRegionManager;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbRegionManagerQuery extends ObjectsQueryBase<TbRegionManager> {
    @Override
    public String getFrom() {
        return "from data.common.TbRegionManager  ";
    }
}

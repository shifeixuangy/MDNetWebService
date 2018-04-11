package dataDAO.impl;

import data.common.TbProjManager;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbProjManagerQuery extends ObjectsQueryBase<TbProjManager>  {
    @Override
    public String getFrom() {
        return "from data.common.TbProjManager ";
    }
}

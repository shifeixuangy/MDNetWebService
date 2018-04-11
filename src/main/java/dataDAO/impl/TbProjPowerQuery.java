package dataDAO.impl;

import data.common.TbProjPower;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbProjPowerQuery extends ObjectsQueryBase<TbProjPower> {
    @Override
    public String getFrom() {
        return "from data.common.TbProjPower ";
    }
}

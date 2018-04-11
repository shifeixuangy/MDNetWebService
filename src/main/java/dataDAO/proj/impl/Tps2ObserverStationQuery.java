package dataDAO.proj.impl;

import data.proj.TbTps2ObserverStation;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by admin on 2016/4/22.
 */
public class Tps2ObserverStationQuery extends ObjectsQueryBase<TbTps2ObserverStation> {
    @Override
    public String getFrom() {
        return " from  data.proj.TbTps2ObserverStation ";
    }
}

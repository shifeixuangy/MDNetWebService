package dataDAO.proj.impl;

import data.proj.TbTps2KonwnPoint;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by admin on 2016/4/22.
 */
public class Tps2KonwPointQuery extends ObjectsQueryBase<TbTps2KonwnPoint> {
    @Override
    public String getFrom() {
        return " from data.proj.TbTps2KonwnPoint ";
    }
}

package dataDAO.proj.impl;

import data.proj.TbTps2Project;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by admin on 2016/4/22.
 */
public class Tps2ProjectQuery extends ObjectsQueryBase<TbTps2Project> {
    @Override
    public String getFrom() {
        return " from data.proj.TbTps2Project  ";
    }
}

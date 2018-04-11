package dataDAO.proj.impl;

import data.proj.TbGroup;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class GroupQuery extends ObjectsQueryBase<TbGroup> {
    @Override
    public String getFrom() {
        return " from data.proj.TbGroup";
    }
}

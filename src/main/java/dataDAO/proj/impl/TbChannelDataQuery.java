package dataDAO.proj.impl;

import data.proj.TbChannelData;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/12/4.
 */
public class TbChannelDataQuery extends ObjectsQueryBase<TbChannelData> {
    @Override
    public String getFrom() {
        return " from data.proj.TbChannelData  ";
    }
}

package dataDAO.proj.impl;

import data.proj.TbChannel;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/12/4.
 */
public class TbChannelQuery extends ObjectsQueryBase<TbChannel> {
    @Override
    public String getFrom() {
        return " from data.proj.TbChannel  ";
    }
}

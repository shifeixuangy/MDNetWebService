package dataDAO.proj.impl;

import data.proj.TbVolume;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/9/8.
 */
public class TbVolumeQuery extends ObjectsQueryBase<TbVolume> {
    @Override
    public String getFrom() {
        return "  from data.proj.TbVolume  ";
    }
}

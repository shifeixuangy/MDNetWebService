package dataDAO.proj.impl;

import data.proj.TbImageMeta;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2016/1/14.
 */
public class TbImageMetaQuery extends ObjectsQueryBase<TbImageMeta> {
    @Override
    public String getFrom() {
        return " from data.proj.TbImageMeta  ";
    }
}

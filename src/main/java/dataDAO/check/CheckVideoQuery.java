package dataDAO.check;

import data.proj.CheckVideo;
import dataDAO.impl.ObjectsQueryBase;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class CheckVideoQuery extends ObjectsQueryBase<CheckVideo> {
    @Override
    public String getFrom() {
        return " from data.proj.CheckVideo ";
    }
}

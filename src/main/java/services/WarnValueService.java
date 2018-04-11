package services;

import data.proj.TbWarnValue;
import data.request.parameters.AddAndUpdateWarnValueParameter;
import data.request.parameters.DeleteWarnValueParameter;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/6.
 */
public class WarnValueService {
    public List<TbWarnValue>getWarnValueList(int projID)
    {
        return ProjQueryHelper.getWarnValue(projID);
    }

    public void addWarnValue(AddAndUpdateWarnValueParameter pa)
    {
        DataHelper.add(pa.toWarnValue(),pa.getProjID());
    }

    public void updateWarnValue(AddAndUpdateWarnValueParameter pa)
    {
        TbWarnValue wv=ProjQueryHelper.getWarnValue(pa.getProjID(),pa.getSensorType(),pa.getWarnType(),pa.getWarnLevel());
        wv.setValue((float)pa.getValue());
        wv.setValid(pa.isValid());
        DataHelper.update(wv,pa.getProjID());
    }


    public void deleteWarnValue(DeleteWarnValueParameter pa)
    {
        TbWarnValue wv=ProjQueryHelper.getWarnValue(pa.getProjID(),pa.getSensorType(),pa.getWarnType(),pa.getWarnLevel());
        DataHelper.delete(wv,pa.getProjID());
    }






}

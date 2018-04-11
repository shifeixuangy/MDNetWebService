package services;

import data.proj.TbWarnRevise;
import data.request.parameters.AddAndUpdateWarnReviseParameter;
import data.request.parameters.DeleteWarnReviseParameter;
import data.response.results.WarnReviseResult;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/6.
 */
public class WarnReviseService {
    public List<WarnReviseResult>getWarnReviseList(int projID)
    {
        return ProjQueryHelper.getWarnReviseResult(projID);
    }

    public void addWarnRevise(AddAndUpdateWarnReviseParameter pa)
    {
        DataHelper.add(pa.toWarnRevise(),pa.getProjID());
    }

    public void updateWarnRevise(AddAndUpdateWarnReviseParameter pa)
    {
        TbWarnRevise wr=ProjQueryHelper.getWarnRevise(pa.getProjID(),pa.getSensorType(),pa.getSensorID(),pa.getWarnChildType());
        wr.setValue((float)pa.getValue());
        wr.setValid(pa.isValid());
        DataHelper.update(wr,pa.getProjID());
    }

    public void deleteWarnRevise(DeleteWarnReviseParameter pa)
    {
        TbWarnRevise wr=ProjQueryHelper.getWarnRevise(pa.getProjID(),pa.getSensorType(),pa.getSensorID(),pa.getWarnChildType());
        DataHelper.delete(wr,pa.getProjID());
    }




}

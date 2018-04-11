package services;

import data.proj.TbWarnPerson;
import data.request.parameters.AddAndUpdateWarnPersonParameter;
import data.request.parameters.DeleteWarnPersonParameter;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class WarnPersonService {
    public List<TbWarnPerson>getWarnPersonList(int projID)
    {
        return ProjQueryHelper.getWarnPersonList(projID);
    }

    public void addWarnPerson(AddAndUpdateWarnPersonParameter pa)
    {
        DataHelper.add(pa.toTbWarnPerson(),pa.getProjID());
    }

    public void updateWarnPerson(AddAndUpdateWarnPersonParameter pa)
    {
        TbWarnPerson wp=ProjQueryHelper.getWarnPerson(pa.getProjID(),pa.getUserName());
        if(wp==null)
            return;
        wp.setUserPhone(pa.getUserPhone());
        wp.setUserEmail(pa.getUserEmail());
        wp.setUserWarnLevel((short)pa.getUserWarnLevel());
        wp.setPosition(pa.getPosition());
        wp.setCompanyID(pa.getCompanyID());
        DataHelper.update(wp,pa.getProjID());
    }
    public void deleteWarnPerson(DeleteWarnPersonParameter pa)
    {
        TbWarnPerson wp=ProjQueryHelper.getWarnPerson(pa.getProjID(),pa.getUserName());
        DataHelper.delete(wp,pa.getProjID());
    }

}

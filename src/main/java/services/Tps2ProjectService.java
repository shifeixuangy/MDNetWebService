package services;

import data.proj.TbTps2Project;
import data.request.parameters.AddAndUpdateTp2ProjectParameter;
import data.request.parameters.GetAndDeleteTps2ProjectParameter;
import dataDAO.DataHelper;
import dataDAO.query.helper.Tps2QueryHelper;

import java.util.List;

/**
 * Created by admin on 2016/4/22.
 */
public class Tps2ProjectService {
    public List<TbTps2Project>getTps2ProjectList(GetAndDeleteTps2ProjectParameter pa)
    {
        return Tps2QueryHelper.getProject(pa.getProjID(),pa.getId());
    }

    public Integer addTps2Project(AddAndUpdateTp2ProjectParameter pa)
    {
        pa.getData().setId(0);
        return DataHelper.add(pa.getData(),pa.getProjID());
    }

    public void updateTps2Project(AddAndUpdateTp2ProjectParameter pa)
    {
        DataHelper.update(pa.getData(),pa.getProjID());
    }

    public void deleteTps2Project(GetAndDeleteTps2ProjectParameter pa)
    {
        TbTps2Project pi=new TbTps2Project();
        pi.setId(pa.getId());
        DataHelper.delete(pi,pa.getProjID());
    }



}

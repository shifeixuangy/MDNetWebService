package services;

import data.proj.TbVolume;
import data.request.parameters.AddAndUpdateVolumeParameter;
import data.request.parameters.DeleteVolumeParameter;
import dataDAO.DataHelper;
import dataDAO.interfaces.ObjectsQuery;
import dataDAO.proj.impl.TbVolumeQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/9/8.
 */
public class VolumeService {


    public int addVolume(AddAndUpdateVolumeParameter pa)
    {
        TbVolume tv=pa.toTbVolume();
        return DataHelper.add(tv,pa.getProjID());
    }


    public void updateVolume(AddAndUpdateVolumeParameter pa)
    {
        TbVolume tv=pa.toTbVolume();
        DataHelper.update(tv,pa.getProjID());
    }


    public void deleteVolume(DeleteVolumeParameter pa)
    {
        TbVolume tv=new TbVolume();
        tv.setId(pa.getId());
        DataHelper.delete(tv,pa.getProjID());
    }

    public List<TbVolume>getVolumes(int projID)
    {
        ObjectsQuery<TbVolume>vQuery=new TbVolumeQuery();
        return vQuery.query(null,projID);
    }



}

package services;

import data.proj.TbMonitorArea;
import data.request.parameters.AddMonitorAreaParameter;
import data.request.parameters.DeleteMonitorAreaParameter;
import data.request.parameters.UpdateMonitorAreaParameter;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class MonitorAreaService {
    public List<TbMonitorArea>getMonitorAreaList(int projID)
    {
        return ProjQueryHelper.getMonitorArea(projID,-1);
    }

    public Integer addMonitorArea(AddMonitorAreaParameter pa)
    {
        return DataHelper.add(pa.toTbMonitorArea(),pa.getProjID());
    }
    public void updateMonitorArea(UpdateMonitorAreaParameter pa)
    {
        DataHelper.update(pa.toTbMonitorArea(),pa.getProjID());
    }

    public void deleteMonitorArea(DeleteMonitorAreaParameter pa)
    {
        TbMonitorArea ma=new TbMonitorArea();
        ma.setId(pa.getAreaID());
        DataHelper.delete(ma,pa.getProjID());
    }

}

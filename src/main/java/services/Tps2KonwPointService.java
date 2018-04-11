package services;

import data.proj.TbTps2KonwnPoint;
import data.request.parameters.AddAndUpdateTps2KnowPointParameter;
import data.request.parameters.GetAndDeleteTps2KnowPointParameter;
import dataDAO.DataHelper;
import dataDAO.query.helper.Tps2QueryHelper;

import java.util.List;

/**
 * Created by admin on 2016/4/22.
 */
public class Tps2KonwPointService {
    public List<TbTps2KonwnPoint>getKonwPointList(GetAndDeleteTps2KnowPointParameter pa)
    {
        return Tps2QueryHelper.getKonwPointList(pa.getProjID(),pa.getId());
    }

    public Integer addTps2KonwPoint(AddAndUpdateTps2KnowPointParameter pa)
    {
        return DataHelper.add(pa.getData(),pa.getProjID());
    }

    public void updateTps2KonwPoint(AddAndUpdateTps2KnowPointParameter pa)
    {
        DataHelper.update(pa.getData(),pa.getProjID());
    }

    public void deleteTps2KonwPoint(GetAndDeleteTps2KnowPointParameter pa)
    {
        TbTps2KonwnPoint point=new TbTps2KonwnPoint();
        point.setId(pa.getId());
        DataHelper.delete(point,pa.getProjID());
    }
}

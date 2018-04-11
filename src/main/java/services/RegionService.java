package services;

import commonUtil.CommonVariable;
import data.common.TbRegion;
import data.request.parameters.AddRegionParameter;
import data.request.parameters.UpdateRegionParameter;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class RegionService {
    public List<TbRegion>getRegionList()
    {
        return ProjQueryHelper.getRegion(CommonVariable.DEFAULT_PROJID,-1);
    }

    public TbRegion getRegion(int regionID)
    {
        List<TbRegion>res=ProjQueryHelper.getRegion(CommonVariable.DEFAULT_PROJID,regionID);
        return  res.size()>0?res.get(0):null;
    }

    public Integer addRegion(AddRegionParameter pa)
    {
        return DataHelper.add(pa.toTbRegion(),CommonVariable.DEFAULT_PROJID);
    }

    public void updateRegion(UpdateRegionParameter pa)
    {
        DataHelper.update(pa.toTbRegion(),CommonVariable.DEFAULT_PROJID);
    }
    public void deleteRegion(int regionID)
    {
        TbRegion re=new TbRegion();
        re.setId(regionID);
        DataHelper.delete(re,CommonVariable.DEFAULT_PROJID);
    }


}

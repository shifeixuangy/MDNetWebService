package services;

import commonUtil.CommonVariable;
import data.common.TbRegion;
import data.common.TbRegionManager;
import data.common.TbUser;
import data.request.parameters.DeleteRegionMgrParameter;
import data.response.results.GetRegionMgrListResult;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class RegionMgrService {
    public List<GetRegionMgrListResult>getRegionMgrList()
    {
        List<GetRegionMgrListResult>results=new LinkedList<>();
        CommonQueryHelper.getRegionManager().forEach(rm->{
            GetRegionMgrListResult re=new GetRegionMgrListResult();
            re.setId(rm.getId());
            re.setUid(rm.getUid());
            re.setRegionID(rm.getRegionId());
            re.setAdmin(rm.isAdmin());
            TbUser user=CommonQueryHelper.getUser(rm.getUid());
           List<TbRegion> region= ProjQueryHelper.getRegion(CommonVariable.DEFAULT_PROJID,rm.getRegionId());
            if(user!=null)
            {
                re.setUserName(user.getName());
            }
            if(region.size()>0)
                re.setRegionName(region.get(0).getRegionName());
            results.add(re);
        });
        return results;
    }

    public void addRegionMgr(TbRegionManager rm)
    {
        DataHelper.add(rm,CommonVariable.DEFAULT_PROJID);
    }


    public void updateRegionMgr(TbRegionManager rm)
    {
        TbRegionManager rmDB= CommonQueryHelper.getRegionManager(rm.getRegionId(),rm.getUid());
        if(rmDB==null)
            return;
        rmDB.setAdmin(rm.isAdmin());
        DataHelper.update(rmDB,CommonVariable.DEFAULT_PROJID);
    }

    public void deleteRegionMgr(DeleteRegionMgrParameter pa)
    {
        DataHelper.deleteRegionMgr(pa.getUid(),pa.getRegionID());
    }

}



















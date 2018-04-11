package services;

import commonUtil.CommonVariable;
import data.common.*;
import data.request.parameters.DeleteProjMgrParameter;
import data.response.results.GetProjMgrListResult;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.ExecuteSql;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class ProjMgrService {
    public List<GetProjMgrListResult>getProjMgrList()
    {
        List<GetProjMgrListResult>results=new LinkedList<>();
        CommonQueryHelper.getProjManager().forEach(pm->{
            GetProjMgrListResult pmResult=new GetProjMgrListResult();
            TbUser user=CommonQueryHelper.getUser(pm.getUid());
            TbProjInfo pi=CommonQueryHelper.getProjInfo(pm.getProjId());
            List<TbProjPower>projPower=CommonQueryHelper.getProjPower(pm.getPowerId());
            pmResult.setId(pm.getId());
            pmResult.setUid(user.getId());
            pmResult.setUserName(user.getName());
            pmResult.setProjID(pm.getProjId());
            pmResult.setProjName(pi.getName());
            pmResult.setPowerID(pm.getPowerId());
            pmResult.setPowerName(projPower.size()>0?projPower.get(0).getPowerName():null);
            results.add(pmResult);
        });
        return results;
    }

    public Integer addProjMgr(TbProjManager pm)
    {
        Integer id= DataHelper.add(pm, CommonVariable.DEFAULT_PROJID);
        //授予了管理员权限
        if(pm.getPowerId()== (Permission.ADMIN.intValue()))
        {
            TbUser u=CommonQueryHelper.getUser(pm.getUid());
            if(u!=null)
            {
                u.setAdmin(true);
                DataHelper.update(u,CommonVariable.DEFAULT_PROJID);
            }
        }
        return id;
    }

    public void updateProjMgr(TbProjManager pm)
    {
        TbProjManager pmMemory=CommonQueryHelper.getProjManager(pm.getProjId(),pm.getUid());
        if(pmMemory!=null) {
            pm.setId(pmMemory.getId());
            DataHelper.update(pm, CommonVariable.DEFAULT_PROJID);
        }
    }
    public void deleteProjMgr(DeleteProjMgrParameter pa)
    {
        DataHelper.deleteProjMgr(pa.getUid(),pa.getProjID());
    }

}




















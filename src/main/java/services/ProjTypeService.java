package services;

import commonUtil.CommonVariable;
import data.common.TbProjType;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;

import java.util.List;

/**
 * Created by admin on 2016/6/13.
 */
public class ProjTypeService {
    public List<TbProjType>getProjTypes(int projTypeID)
    {
        return CommonQueryHelper.getProjTypes(projTypeID);
    }

    public int addProjType(TbProjType projType)
    {
        return DataHelper.add(projType, CommonVariable.DEFAULT_PROJID);
    }

    public void updateProjType(TbProjType projType)
    {
        TbProjType projTypeMemory=CommonQueryHelper.getProjType(projType.getId());
        if(projTypeMemory==null)
            return;
        boolean isChange=false;
        if(projTypeMemory.isNameUpdateable()) {
            projTypeMemory.setName(projType.getName());
            isChange=true;
        }
        if(projTypeMemory.isAliasUpdateable()) {
            projTypeMemory.setAlias(projType.getAlias());
            isChange=true;
        }
        if(isChange)
            DataHelper.update(projTypeMemory,CommonVariable.DEFAULT_PROJID);
    }

    public  void deleteProjType(int projTypeID)
    {
        TbProjType projType=CommonQueryHelper.getProjType(projTypeID);
        if(projType==null)
            return;
        if(!projType.isDeleteable())
            return;
        DataHelper.delete(projType,CommonVariable.DEFAULT_PROJID);
    }

}

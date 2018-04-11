package services;

import commonUtil.CommonVariable;
import data.proj.TbGroup;
import data.request.parameters.AddGroupParameter;
import data.request.parameters.DeleteGroupParameter;
import data.request.parameters.UpdateGroupParameter;
import dataDAO.DataHelper;
import dataDAO.ExecuteSql;
import dataDAO.ProjQueryHelper;

import java.util.List;
import java.util.Optional;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class GroupService {
    public List<TbGroup>getGroupList(int projID)
    {
        return ProjQueryHelper.getGroup(projID,-1);
    }

    public Integer addGroup(AddGroupParameter pa)
    {
        return DataHelper.add(pa.toTbGroup(),pa.getProjID());
    }

    public void updateGroup(UpdateGroupParameter pa)
    {
        Optional<TbGroup> g=ProjQueryHelper.getGroup(pa.getProjID(),pa.getGroupID()).stream().findFirst();
        if(!g.isPresent())
            return;
        TbGroup group=g.get();
        group.setAlias(pa.getAlias());
        group.setNote(pa.getNote());
        group.setExValues(pa.getExValues());
        DataHelper.update(group,pa.getProjID());
    }

    public void deleteGroup(DeleteGroupParameter pa)
    {
        DataHelper.delete(pa.toTbGroup(),pa.getProjID());
        //设置tb_sensor_attri
//        ExecuteSql.execute(pa.getProjID(),"update tb_sensor_attri set GroupID=null where GroupID="+Integer.toString(pa.getGroupID()));
    }

}

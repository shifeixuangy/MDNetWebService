package services;

import data.proj.CheckPerson;
import data.request.parameters.AddCheckPersonParameter;
import data.request.parameters.DeleteCheckImageParameter;
import data.request.parameters.DeleteCheckPersonParameter;
import dataDAO.DataHelper;
import dataDAO.ExecuteSql;
import dataDAO.check.CheckQuery;
import dataDAO.check.CheckQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/11/4.
 */
public class CheckPersonService {
    public List<CheckPerson>getCheckPerson(int projID,int checkID,int userID)
    {
        return CheckQueryHelper.getCheckPerson(projID, checkID, userID);
    }

    public Integer addCheckPerson(AddCheckPersonParameter pa)
    {
        return DataHelper.add(pa.getCheckPerson(),pa.getProjID());
    }

    public void deleteCheckPerson(DeleteCheckPersonParameter pa)
    {
        String sql="delete from check_person where CheckID="+Integer.toString(pa.getCheckID())+" and UserID="+Integer.toString(pa.getUserID());
        ExecuteSql.execute(pa.getProjID(),sql);
    }

}

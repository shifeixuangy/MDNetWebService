package services;

import data.proj.Check;
import dataDAO.DataHelper;
import dataDAO.check.CheckQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class CheckService {
    public List<Check>getCheck(int projID,int checkID)
    {
        return CheckQueryHelper.getCheck(projID,checkID);
    }


    public Integer add(int projID,Check check)
    {
       return   DataHelper.add(check,projID);
    }

    public void update(int projID,Check check)
    {
        DataHelper.update(check,projID);
    }

    public void delete(int projID,int checkID)
    {
        Check chk=new Check();
        chk.setId(checkID);
        DataHelper.delete(chk,projID);
    }


}

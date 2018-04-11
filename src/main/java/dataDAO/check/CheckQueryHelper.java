package dataDAO.check;

import commonUtil.CommonVariable;
import data.proj.*;
import dataDAO.interfaces.ObjectsQuery;

import java.util.List;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class CheckQueryHelper {
    public static List<Check> getCheck(int projID, int checkID) {
        ObjectsQuery<Check> checkQuery = new CheckQuery();
        if (checkID == CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return checkQuery.query(null, projID);
        String where = " ID=" + Integer.toString(checkID);
        return checkQuery.query(where, projID);
    }

    public static List<Record>getRecord(int projID,int checkID,int recordID)
    {
        ObjectsQuery<Record>recordQuery=new RecordQuery();
        String where=" CheckID="+Integer.toString(checkID)+"   ";
        if(recordID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where+=" and ID="+Integer.toString(recordID);
        return recordQuery.query(where,projID);
    }

    public static List<CheckImage>getImage(int projID,int checkID,int recordID,int imgID)
    {
        ObjectsQuery<CheckImage>imgQuery=new CheckImageQuery();
        String where=null;
        if(imgID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where=" ID="+Integer.toString(imgID);
        else
            where=" CheckID="+Integer.toString(checkID)+" and RecordID="+Integer.toString(recordID)+"  ";
        return imgQuery.query(where,projID);
    }

    public static List<CheckVideo>getVideo(int projID,int checkID,int recordID,int videoID)
    {
        ObjectsQuery<CheckVideo>videoQuery=new CheckVideoQuery();
        String where=null;
        if(videoID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where=" ID="+Integer.toString(videoID);
        else
            where=" CheckID="+Integer.toString(checkID)+" and RecordID="+Integer.toString(recordID)+"  ";
        return videoQuery.query(where,projID);
    }

    public static List<CheckPerson>getCheckPerson(int projID,int checkID,int userID)
    {
        ObjectsQuery<CheckPerson>cpQuery=new CheckPersonQuery();
        String where=" CheckID="+Integer.toString(checkID)+" ";
        if(userID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where+=" and UserID="+Integer.toString(userID);
        return cpQuery.query(where,projID);
    }


}

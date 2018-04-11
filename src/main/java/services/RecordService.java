package services;

import data.proj.Record;
import data.request.parameters.GetAndDeleteRecordParameter;
import dataDAO.DataHelper;
import dataDAO.check.CheckQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/11/2.
 */
public class RecordService {
    public List<Record> getRecord(GetAndDeleteRecordParameter pa) {
        return CheckQueryHelper.getRecord(pa.getProjID(), pa.getCheckID(), pa.getRecordID());
    }

    public Integer add(int projID, Record r) {
        return DataHelper.add(r, projID);
    }

    public void update(int projID,Record r)
    {
        DataHelper.update(r,projID);
    }

    public void delete(int projID,int recordID)
    {
        Record r=new Record();
        r.setId(recordID);
        DataHelper.delete(r,projID);
    }



}

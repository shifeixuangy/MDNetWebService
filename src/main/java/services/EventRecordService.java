package services;

import commonUtil.CommonVariable;
import data.proj.TbEventRecord;
import data.request.parameters.AddAndUpdateEventRecordParameter;
import data.request.parameters.DeleteEventRecordParameter;
import data.request.parameters.GetEventRecordListParameter;
import dataDAO.DataHelper;
import dataDAO.proj.impl.TbEventRecordQuery;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/6.
 */
public class EventRecordService {
    public Integer addEventRecord(AddAndUpdateEventRecordParameter pa)
    {
        return DataHelper.add(pa.getEventRecord(),pa.getProjID());
    }

    public void updateEventRecord(AddAndUpdateEventRecordParameter pa)
    {
        DataHelper.update(pa.getEventRecord(),pa.getProjID());
    }

    public void deleteEventRecord(DeleteEventRecordParameter pa)
    {
        TbEventRecord er=new TbEventRecord();
        er.setId(pa.getEventRecordID());
        DataHelper.delete(er,pa.getProjID());
    }

    public List<TbEventRecord>getEventRecordList(GetEventRecordListParameter pa)
    {
        DateFormat df=CommonVariable.getDefaultDateFormat();
        String where=" Time between '"+df.format(pa.getBeginTime())+"' and '"+df.format(pa.getEndTime())+"' ";
        TbEventRecordQuery erq=new TbEventRecordQuery();
        return erq.query(where,pa.getProjID());
    }
}

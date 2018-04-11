package services;

import commonUtil.CollectionUtil;
import data.common.MonitorTypeStatus;
import data.proj.TbMonitorType;
import data.request.parameters.GetMonitorTypeListParameter;
import data.request.parameters.UpdateMonitorTypeParameter;
import data.response.results.GetMonitorTypeListResult;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
public class MonitorTypeService {
    public List<GetMonitorTypeListResult>getMonitorTypeList(GetMonitorTypeListParameter pa)
    {
        List<GetMonitorTypeListResult>result=new LinkedList<>();
        List<TbMonitorType>monitorTypes=ProjQueryHelper.getMonitorType(pa.getProjID(), MonitorTypeStatus.valueOf(pa.getStatus()).isValid());
        if(CollectionUtil.isNullOrEmpty(monitorTypes))
            return result;
        monitorTypes.forEach(mt->{
            GetMonitorTypeListResult re=new GetMonitorTypeListResult(mt);
            int sensorCount= ProjQueryHelper.getSensorList(pa.getProjID(),mt.getSensorType()).size();
            re.setSensorCount(sensorCount);
            result.add(re);
        });
        if(!CollectionUtil.isNullOrEmpty(result))
            Collections.sort(result,GetMonitorTypeListResult.COMPARATOR);
        return  result;
    }

    public void updateMonitorType(UpdateMonitorTypeParameter pa)
    {
        if(pa==null)
            return;
        TbMonitorType mt=pa.toTbMonitorType();
        DataHelper.update(mt,pa.getProjID());
    }
}

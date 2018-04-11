package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import commonUtil.CommonVariable;
import commonUtil.ReflectUtil;
import commonUtil.SensorDataSelects;
import commonUtil.StringUtil;
import data.common.SensorType;
import data.proj.SensorData;
import data.request.parameters.DeleteSensorDataRecordParameter;
import data.request.parameters.UpdateSensorDataRecordParameter;
import dataDAO.DataHelper;

/**
 * Created by liudongdong on 2016/2/18.
 */
public class SensorDataOperationService {

    public void updateSensorDataRecord(UpdateSensorDataRecordParameter pa)
    {
        if(StringUtil.isNullOrEmpty(pa.getSensorData()))
            return;
        SensorType sType=SensorType.valueOf(pa.getSensorType());
        if(sType==null)
            return;
        Class c=SensorDataSelects.getSensorClass(sType,pa.isAvg());
        if(c==null)
            return;
        ObjectMapper objectMapper= CommonVariable.getObjectMapper();
        try {
            Object data = objectMapper.readValue(pa.getSensorData(), c);
            if(data==null)
                return;
            DataHelper.update(data,pa.getProjID());
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

    }

    public void deleteSensorDataRecord(DeleteSensorDataRecordParameter pa)
    {
        SensorType sType=SensorType.valueOf(pa.getSensorType());
        if(sType==null)
            return;
        Class c=SensorDataSelects.getSensorClass(sType,pa.isAvg());
        if(c==null)
            return;
        Object data= ReflectUtil.getDataObject(pa.getID(),c);
        if(data==null)
            return;
        DataHelper.delete(data,pa.getProjID());
    }
}

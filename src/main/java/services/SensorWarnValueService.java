package services;

import commonUtil.CollectionUtil;
import data.common.SensorType;
import data.proj.TbSensorAttri;
import data.proj.TbSensorWarnValue;
import data.request.parameters.AddAndUpdateSensorWarnValueParameter;
import data.request.parameters.DeleteSensorWarnValueParameter;
import data.request.parameters.GetSensorWarnValueListParameter;
import data.response.results.GetSensorWarnValueListResult;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by liudongdong on 2015/11/13.
 */
public class SensorWarnValueService {
    public List<GetSensorWarnValueListResult> getSensorWarnValueList(GetSensorWarnValueListParameter pa) {
        List<TbSensorWarnValue> wvValues = ProjQueryHelper.getSensorWarnValueList(pa.getProjID(), pa.getSensorID());
        if (CollectionUtil.isNullOrEmpty(wvValues))
            return null;
        List<GetSensorWarnValueListResult> result = new LinkedList<>();
        List<TbSensorAttri> sensors = ProjQueryHelper.getSensorList(pa.getProjID(), SensorType.All.toInt());
        wvValues.forEach(wv -> {
            GetSensorWarnValueListResult r = new GetSensorWarnValueListResult(wv);
            Optional<TbSensorAttri> sensor = sensors.stream().filter(t -> t.getId() == wv.getSensorID()).findFirst();
            if(sensor.isPresent())
            {
                r.setSensorName(sensor.get().getName());
                r.setSensorAlias(sensor.get().getAlias());
            }
            result.add(r);
        });
        return result;
    }


    public Integer addSensorWarnValue(AddAndUpdateSensorWarnValueParameter pa) {
        return DataHelper.add(pa.getSensorWarnValue(), pa.getProjID());
    }

    public void updateSensorWarnValue(AddAndUpdateSensorWarnValueParameter pa) {
        DataHelper.update(pa.getSensorWarnValue(), pa.getProjID());
    }

    public void deleteSensorWarnValue(DeleteSensorWarnValueParameter pa) {
        TbSensorWarnValue swv = new TbSensorWarnValue();
        swv.setId(pa.getID());
        DataHelper.delete(swv, pa.getProjID());
    }

}

package services;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import commonUtil.TimeUtil;
import data.common.SensorType;
import data.proj.TPSExValues;
import data.proj.TbSensorAttri;
import data.proj.TbTpsData;
import data.request.parameters.AddTPSDataRequestParameter;
import data.request.parameters.TPSConfig;
import data.request.parameters.TPSData;
import dataDAO.DataHelper;
import dataDAO.ExecuteSql;
import dataDAO.ProjQueryHelper;
import dataDAO.proj.impl.TPSOperation;
import dataDAO.proj.impl.TPSQuery;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by liudongdong on 2015/12/15.
 */
public class TPSService {
    public List<TbTpsData> getMinTimeData(int projID,int sensorID)
    {
        TPSQuery tq=new TPSQuery();
        return tq.getMinTimeData(projID, sensorID);
    }
    public void adddTPSData(AddTPSDataRequestParameter pa) {
        if (CollectionUtil.isNullOrEmpty(pa.getDatas()))
            return;
        pa.getDatas().forEach(tpsDataWrapper -> {
            TPSConfig config = tpsDataWrapper.getConfig();
            TbSensorAttri attri = new TbSensorAttri();
            attri.setName(config.getSensorName());
            attri.setAlias(config.getSensorName());
            attri.setNote(config.getNote());
            attri.setSensorType(SensorType.TYPE_TPS.toInt());
            attri= checkAndGetTPSAttri(pa.getProjID(), attri);
            final  TbSensorAttri attriFinal=attri;
            TPSExValues exValues;
            try {
                if (StringUtil.isNullOrEmpty(attri.getExValues()))
                    exValues = new TPSExValues();
                else
                    exValues = CommonVariable.getObjectMapper().readValue(attri.getExValues(), TPSExValues.class);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            List<TPSData> datas = tpsDataWrapper.getDatas();
            if (!CollectionUtil.isNullOrEmpty(datas)) {
                Collections.sort(datas,new TPSData.TPSDataComparator());
                checkAndInitTPSExvalues(attri,exValues,datas.get(0),pa.getProjID());
                datas.forEach(tpsData -> {
                    TbTpsData tbTpsData=new TbTpsData();
                    tbTpsData.setSensorId(attriFinal.getId());
                    calculate(pa.getProjID(),tbTpsData,tpsData,exValues);
                    try {
                        TPSOperation to=new TPSOperation();
                        to.addTPSData(pa.getProjID(),tbTpsData,false);
                    }
                    catch (Exception ex)
                    {
                        throw ex;
                    }
                });
            }

        });
    }
    private void checkAndInitTPSExvalues(TbSensorAttri attri,TPSExValues exValues,TPSData data,int projID)
    {
        if(exValues.getInitX()==0&&exValues.getInitY()==0&& exValues.getInitH()==0)
        {
            exValues.setInitX(data.getX());
            exValues.setInitY(data.getY());
            exValues.setInitH(data.getH());
            try {
                attri.setExValues(CommonVariable.getObjectMapper().writeValueAsString(exValues));
            }
            catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }
            DataHelper.update(attri,projID);
        }
    }

    private TbSensorAttri checkAndGetTPSAttri(int projID, TbSensorAttri attri) {
        List<TbSensorAttri> attris = ProjQueryHelper.getSensorList(projID, SensorType.TYPE_TPS.toInt());
        if (CollectionUtil.isNullOrEmpty(attris))
            attris = Collections.emptyList();
        Optional<TbSensorAttri> aiOptional = attris.stream().filter(t -> attri.getName().equals(t.getName())).findFirst();
        if (!aiOptional.isPresent()) {
            Integer id = DataHelper.add(attri, projID);
            assert id.equals(attri.getId());
            return attri;
        } else {
            return aiOptional.get();
        }
    }

    private void calculate(int projID, TbTpsData tbTpsData,TPSData tpsData,TPSExValues exValues)
    {
        tbTpsData.setDispX(tpsData.getX()-exValues.getInitX());
        tbTpsData.setDispY(tpsData.getY()-exValues.getInitY());
        tbTpsData.setDispH(tpsData.getH()-exValues.getInitH());
        tbTpsData.setTime(tpsData.getTime());
        
        TPSQuery tq=new TPSQuery();
        TbTpsData preData=tq.getPreData(projID,tbTpsData.getSensorId(),tbTpsData.getTime());
        if(preData==null)
            return;
        double day= TimeUtil.minusDay(preData.getTime(),tbTpsData.getTime());
        if(day==0)
            return;
        tbTpsData.setVelocityX((tbTpsData.getDispX()-preData.getDispX())/day);
        tbTpsData.setVelocityY((tbTpsData.getDispY()-preData.getDispY())/day);
        tbTpsData.setVelocityH((tbTpsData.getDispH()-preData.getDispH())/day);
    }
}

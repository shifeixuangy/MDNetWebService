package init.config;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.TimeUtil;
import data.common.SensorType;
import data.common.TbProjInfo;
import data.proj.TbSensorAttri;
import data.proj.TbTpsData;
import dataDAO.CommonQueryHelper;
import dataDAO.ProjQueryHelper;
import dataDAO.proj.impl.TPSOperation;
import dataDAO.proj.impl.TPSQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/12/20.
 */
public class CaculateService {
    private static Log logger=LogFactory.getLog(CaculateService.class);

    private static CaculateService cs;
    public static void init()
    {
        cs=new CaculateService();
        cs.startService();
    }

    public void startService()
    {
        LocalDateTime now=LocalDateTime.now();
        LocalDateTime tomorrow=now.plusDays(1);
        tomorrow= tomorrow.withHour(0).withMinute(5).withSecond(0);
        ScheduledExecutorService ses= Executors.newSingleThreadScheduledExecutor();
        long initDelay=Timestamp.valueOf(tomorrow).getTime()-Timestamp.valueOf(now).getTime();
        ses.scheduleAtFixedRate(()->{
            try
            {
                doWork();
            }
            catch (Exception ex)
            {
                logger.error(ex.getMessage(),ex);
            }
        },initDelay,24*60*60*1000, TimeUnit.MILLISECONDS);
    }

    public void doWork()
    {
        if(!CommonVariable.isCalculateAvg())
            return;
        List<TbProjInfo>pis= CommonQueryHelper.getProjInfo();
        if(CollectionUtil.isNullOrEmpty(pis))
            return;
        Timestamp now=Timestamp.valueOf(LocalDateTime.now());
        pis.stream().map(p->p.getId()).collect(Collectors.toList()).forEach(pid->{
            doWorkForProject(pid,now);
        });
    }

    private void doWorkForProject(int projID, Timestamp now)
    {
        List<TbSensorAttri>attris=ProjQueryHelper.getSensorList(projID, SensorType.TYPE_TPS.toInt());
        if(CollectionUtil.isNullOrEmpty(attris))
            return;
        List<Integer>sisd=attris.stream().map(a->a.getId()).collect(Collectors.toList());
        List<Timestamp>times= TimeUtil.getTimePreDayBeginEnd(now);
        sisd.forEach(id->{
            TPSQuery tq=new TPSQuery();
            List<TbTpsData>datas= tq.getTpsData(projID,id,times.get(0),times.get(1));
            TbTpsData avgData=calculateAvg(datas);
            if(avgData!=null)
            {
                TPSOperation operation=new TPSOperation();
                operation.addTPSData(projID,avgData,true);
            }
        });
    }

    public TbTpsData calculateAvg(List<TbTpsData>datas)
    {
        if(CollectionUtil.isNullOrEmpty(datas))
            return null;
        TbTpsData result=new TbTpsData();
        TbTpsData dataOne=datas.get(0);
        result.setSensorId(dataOne.getSensorId());
        result.setTime(dataOne.getTime());
        int count=datas.size();
        double dx=0,dy=0,dh=0,ax=0,ay=0,ah=0,az=0;
        for(TbTpsData t:datas)
        {
            dx+=t.getDispX();
            dy+=t.getDispY();
            dh+=t.getDispH();
            ax+=t.getVelocityX();
            ay+=t.getVelocityY();
            ah+=t.getVelocityH();
            az+=t.getAzimuth();
        }
        result.setDispX(dx/count);
        result.setDispY(dy/count);
        result.setDispH(dh/count);
        result.setVelocityX(ax/count);
        result.setVelocityY(ay/count);
        result.setVelocityH(ah/count);
        result.setAzimuth(az/count);
        return result;
    }
}

package init.config;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.SensorType;
import data.common.TbProjInfo;
import data.common.WarnLogMsg;
import data.proj.TbSensorAttri;
import data.proj.TbWarnLog;
import dataDAO.CommonQueryHelper;
import dataDAO.ProjQueryHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by liudongdong on 2015/12/25.
 */
public class TagDebugService {
    private static Log logger= LogFactory.getLog(TagDebugService.class);

    public static TagDebugService getInstance() {
        return new TagDebugService();
    }

    public void start() {
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleAtFixedRate(() -> {
            try {
                ProjAndSensor pas = getData();
                TbProjInfo pi = pas.getProj();
                TbSensorAttri attri = pas.getSensor();
                TbWarnLog wl = new TbWarnLog();
                wl.setDealed(false);
                wl.setSendFlag(false);
                wl.setSendMessage(CommonVariable.getProjToken() + "-测试消息-" + LocalDateTime.now().toString());
                wl.setSensorId(attri.getId());
                wl.setSensorType(attri.getSensorType());
                wl.setTime(new Timestamp(System.currentTimeMillis()));
                wl.setWarnChildType("子类型");
                wl.setWarnLevel((short) 1);
                wl.setWarnMainType("主类型");

                WarnLogMsg wlm = new WarnLogMsg(pi.getId(), wl);
                wlm.setProjToken(CommonVariable.getProjToken());
                wlm.setProjName(pi.getName());
                wlm.setSensorName(attri.getName());

                PushMessageHandler handler = PushMessageHandler.getInstance();
                handler.onMessageReceiver(wlm);
            }catch (Exception ex)
            {
                logger.error(ex.getMessage(),ex);
            }

        }, 10, 30, TimeUnit.SECONDS);
    }

    private ProjAndSensor getData() {
        ProjAndSensor ps = new ProjAndSensor();
        List<TbProjInfo> pis = CommonQueryHelper.getProjInfo();
        if (CollectionUtil.isNullOrEmpty(pis))
            return null;
        for (TbProjInfo pi : pis) {
            List<TbSensorAttri> attris = ProjQueryHelper.getSensorList(pi.getId(), SensorType.All.toInt());
            if (CollectionUtil.isNullOrEmpty(attris))
                continue;
            ps.setProj(pi);
            ps.setSensor(attris.get(0));
            break;
        }
        return ps;
    }

    static class ProjAndSensor {
        private TbProjInfo proj;
        private TbSensorAttri sensor;

        public TbProjInfo getProj() {
            return proj;
        }

        public void setProj(TbProjInfo proj) {
            this.proj = proj;
        }

        public TbSensorAttri getSensor() {
            return sensor;
        }

        public void setSensor(TbSensorAttri sensor) {
            this.sensor = sensor;
        }
    }
}

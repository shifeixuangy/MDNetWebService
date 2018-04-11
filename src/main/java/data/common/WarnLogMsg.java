package data.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbSensorAttri;
import data.proj.TbWarnLog;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;
import dataDAO.interfaces.CommonQuery;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by liudongdong on 2015/10/4.
 * Baidu Push Msg Entity
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class WarnLogMsg {
    private String projToken;
    private int projID;
    private String projName;
    private String sensorName;
    private TbWarnLog warnLog;

    public WarnLogMsg() {
    }

    public WarnLogMsg(int projID, TbWarnLog warnLog) {
        this.projID = projID;
        this.warnLog = warnLog;
    }

    public String getProjToken() {
        return projToken;
    }

    public void setProjToken(String projToken) {
        this.projToken = projToken;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public TbWarnLog getWarnLog() {
        return warnLog;
    }

    public void setWarnLog(TbWarnLog warnLog) {
        this.warnLog = warnLog;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public static WarnLogMsg getDebugWarnLogMsg()
    {
        WarnLogMsg msg=new WarnLogMsg(1,new TbWarnLog());
        Optional<TbProjInfo>pi= CommonQueryHelper.getProjInfo().stream().findFirst();
        if(pi.isPresent()) {
            msg.setProjID(pi.get().getId());
            msg.setProjName(pi.get().getName());
        }
        TbWarnLog warnLog=msg.getWarnLog();
        warnLog.setSensorId(1);
        Optional<TbSensorAttri>sensor= ProjQueryHelper.getSensorList(pi.get().getId(),SensorType.All.toInt()).stream().findFirst();
        if(sensor.isPresent())
        {
            msg.setSensorName(sensor.get().getName());
            warnLog.setSensorId(sensor.get().getId());
            warnLog.setSensorType(sensor.get().getSensorType());
        }
        warnLog.setSensorType(SensorType.TYPE_GPS.toInt());
        warnLog.setSendMessage("这是一个随机生成的警报测试");
        warnLog.setTime(new Timestamp(System.currentTimeMillis()));
        warnLog.setDealed(false);
        DataHelper.add(warnLog,pi.get().getId());
        return msg;
    }

}

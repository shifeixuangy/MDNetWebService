package commonUtil;

import data.common.LogType;
import data.common.TbLog;
import data.common.TbUser;
import dataDAO.DataHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LogUtil {
    private static Log logger= LogFactory.getLog(LogUtil.class);
    private static ExecutorService es;
    static
    {
        es=Executors.newFixedThreadPool(1);
    }

    public static void writeLog(final TbLog log)
    {
        if(log.getTime()==null)
            log.setTime(Timestamp.valueOf(LocalDateTime.now()));
        es.execute(()->{
            try {
                DataHelper.add(log, CommonVariable.DEFAULT_PROJID);
            }
            catch (Exception ex)
            {
                logger.error(ex.getMessage(),ex);
            }
        });
    }

    private static void writeLog(int uid, Integer projID, Timestamp time, LogType logType, String logMsg)
    {
        TbLog log=new TbLog();
        log.setUid(uid);
        log.setProjId(projID);
        log.setTime(time);
        log.setLogType(logType.toShort());
        log.setLogMsg(logMsg);
        writeLog(log);
    }

    public static void writeLog(int uid, Integer projID, LogType logType, String logMsg)
    {
        Timestamp now=Timestamp.valueOf(LocalDateTime.now());
        writeLog(uid, projID, now, logType, logMsg);
    }

    public static void writeDealWarnLog(String accessToken,int projID,String note)
    {
        TbUser user=AccessTokenPool.getInstance().getUserInformation(accessToken).getUser();
        TbLog log=new TbLog();
        log.setLogType(LogType.DealWarn.toShort());
        log.setProjId(projID);
        log.setUid(user.getId());
        log.setTime(Timestamp.valueOf(LocalDateTime.now()));
        String userName=user.getName()==null?user.getAccount():user.getName();
        log.setLogMsg("用户<"+userName+">处理了警报，备注信息为："+note);
        writeLog(log);
    }




}

package init.config;

import commonUtil.CommonVariable;
import data.common.TbProjInfo;
import data.common.WarnLogMsg;
import data.proj.TbWarnLog;
import dataDAO.CommonQueryHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PushInit extends HttpServlet {
    private static Log logger = LogFactory.getLog(PushInit.class);
    private static TopicReceiver receiver;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        TopicSender sender = TopicSender.getInstance();
        WarnLogMsg msg = WarnLogMsg.getDebugWarnLogMsg();
        sender.sendWarnLog(msg);
        String msgString = CommonVariable.getObjectMapper().writeValueAsString(msg);
        res.setContentType("text/plain;charset=UTF-8");
        res.getWriter().println("消息推送到云平台:" + msgString);

    }

    @Override
    public void init() throws ServletException {
        if(!CommonVariable.is_PUSH_ENABLE())
            return;
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TagInit.init();
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
                try {
                    initJMSReceiver(0);
                }catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                }
            }
        });

    }

    public static void initJMSReceiver(int delay) {
        Executors.newScheduledThreadPool(1).schedule(new Runnable() {
            @Override
            public void run() {
                initReceiver();
            }
        }, delay, TimeUnit.MINUTES);
    }


    private static void initReceiver() {
        try {
            logger.info("JMS TOPIC订阅初始化开始");
            receiver = TopicReceiver.getInstance();
            receiver.addExceptionListener(new ExceptionHandler() {
                @Override
                public void onException(Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
            });
            receiver.start();
            logger.info("JMS TOPIC订阅初始化成功");
        } catch (Exception ex) {
            initJMSReceiver(CommonVariable.getJmsReceiverInitInterval());
            logger.error(ex.getMessage(), ex);
        }
    }

}

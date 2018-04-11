package init.config;

import commonUtil.CommonVariable;
import data.common.WarnLogMsg;
import data.proj.TbWarnLog;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import static init.config.TopicMessageField.*;

public class TopicReceiver implements MessageListener,Closeable{
    private static final Log logger= LogFactory.getLog(TopicReceiver.class);
    public static final String CLIENT_ID="cn.shmedo.netmonitor";
    private static final SimpleDateFormat sdfNormal=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdfUnNormal=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String addr;
    private String topicName;
    private String uid;
    private String pwd;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private TopicSubscriber topicSubscriber;
    private boolean isException=false;
    private ExceptionHandler exceptionHandler;

    public TopicReceiver(String addr, String topicName, String uid, String pwd) {
        this.addr = addr;
        this.topicName = topicName;
        this.uid = uid;
        this.pwd = pwd;
    }

    public void start() {
        try {
            connectionFactory = new ActiveMQConnectionFactory(uid, pwd, addr);
            connection = connectionFactory.createConnection();
            connection.setClientID(CLIENT_ID);
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(topicName);
            topicSubscriber = session.createDurableSubscriber(topic, "Restful Service Server", null, false);
            topicSubscriber.setMessageListener(this);
            connection.setExceptionListener(new ExceptionListener() {
                @Override
                public void onException(JMSException e) {
                    PushInit.initJMSReceiver(CommonVariable.getJmsReceiverInitInterval());
                    logger.error(e.getMessage(),e);
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }



    @Override
    public void onMessage(Message message) {

        try {
            if ((!message.propertyExists(PROJ_ID))
                    || (!message.propertyExists(SENSOR_ID))) {
//                logger.info("收到一个消息，但不是警报消息");
                return;
            }
            WarnLogMsg warnLogMsg = new WarnLogMsg(-1, new TbWarnLog());
            warnLogMsg.setProjToken(CommonVariable.getProjToken());
            warnLogMsg.setProjID(message.getIntProperty(PROJ_ID));
            if (message.propertyExists(PROJ_NAME))
                warnLogMsg.setProjName(message.getStringProperty(PROJ_NAME));
            if (message.propertyExists(SENSOR_NAME))
                warnLogMsg.setSensorName(message.getStringProperty(SENSOR_NAME));

            TbWarnLog warnLog = warnLogMsg.getWarnLog();
            warnLog.setSensorId(message.getIntProperty(SENSOR_ID));
            if (message.propertyExists(SENSOR_TYPE))
                warnLog.setSensorType(message.getIntProperty(SENSOR_TYPE));
            if (message.propertyExists(WARN_MAIN_TYPE))
                warnLog.setWarnMainType(message.getStringProperty(WARN_MAIN_TYPE));
            if (message.propertyExists(WARN_CHILD_TYPE))
                warnLog.setWarnChildType(message.getStringProperty(WARN_CHILD_TYPE));
            if (message.propertyExists(WARN_LEVEL))
                warnLog.setWarnLevel((short) message.getIntProperty(WARN_LEVEL));
            if (message.propertyExists(SEND_MESSAGE))
                warnLog.setSendMessage(message.getStringProperty(SEND_MESSAGE));
            if (message.propertyExists(TIME)) {
                String timeString = message.getStringProperty(TIME);
                Timestamp t = null;
                if (timeString.contains("-"))
                    t = new Timestamp(sdfNormal.parse(timeString).getTime());
                else
                    t = new Timestamp(sdfUnNormal.parse(timeString).getTime());
                warnLog.setTime(t);
            }

            PushMessageHandler messageHandler = PushMessageHandler.getInstance();
            messageHandler.onMessageReceiver(warnLogMsg);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }

    public boolean isException() {
        return isException;
    }

    public void fireException(boolean isException,Exception ex) {
        this.isException = isException;
        if(isException&&(exceptionHandler!=null))
            exceptionHandler.onException(ex);
    }

    public void addExceptionListener(ExceptionHandler exceptionHandler)
    {
        this.exceptionHandler=exceptionHandler;
    }

    @Override
    public void close() throws IOException {
        try {
            topicSubscriber.close();
            session.close();
            connection.close();
        }
        catch (Exception ex)
        {

        }
    }

    public static TopicReceiver getInstance()
    {
        return  new TopicReceiver(CommonVariable.getTOPIC_ADDR(),CommonVariable.getTOPIC_NAME(),CommonVariable.getTOPIC_UID(),CommonVariable.getTOPIC_PWD());
    }
}

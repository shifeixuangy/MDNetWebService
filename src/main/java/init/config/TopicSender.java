package init.config;

import commonUtil.CommonVariable;
import data.common.WarnLogMsg;
import data.proj.TbWarnLog;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.util.UUID;
import static init.config.TopicMessageField.*;

/**
 * Created by liudongdong on 2015/10/11.
 */
public class TopicSender {
    private static final Log logger= LogFactory.getLog(TopicSender.class);
    private String addr;
    private String topicName;
    private String uid;
    private String pwd;

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;


    public TopicSender(String addr, String topicName, String uid, String pwd) {
        this.addr = addr;
        this.topicName = topicName;
        this.uid = uid;
        this.pwd = pwd;
    }


    public void sendWarnLog(WarnLogMsg msg)
    {
        try {
            connectionFactory = new ActiveMQConnectionFactory(uid, pwd, addr);
            connection = connectionFactory.createConnection();

            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(topicName);
            MessageProducer producer=session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            connection.start();

            TextMessage textMessage=session.createTextMessage();
            textMessage.setIntProperty(PROJ_ID,msg.getProjID());
            textMessage.setStringProperty(PROJ_NAME,msg.getProjName());
            textMessage.setStringProperty(SENSOR_NAME,msg.getSensorName());
            TbWarnLog warnLog=msg.getWarnLog();
            textMessage.setIntProperty(SENSOR_ID,warnLog.getSensorId());
            textMessage.setIntProperty(SENSOR_TYPE,warnLog.getSensorType());
            textMessage.setStringProperty(SEND_MESSAGE,warnLog.getSendMessage());

            producer.send(textMessage);

            producer.close();
            session.close();
            connection.close();

        }catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }

    public void sendWarnHandlerMsg(int projID)
    {
        ConnectionFactory handlerConnectionFactory=new ActiveMQConnectionFactory(uid, pwd, addr);
        Connection handlerConnection=null;
        try
        {
            handlerConnection=handlerConnectionFactory.createConnection();
            Session handlerSession=null;
            try
            {
                handlerSession=handlerConnection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                Destination handlerDestination=handlerSession.createTopic(topicName);
                MessageProducer handlerProducer=handlerSession.createProducer(handlerDestination);
                handlerProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                handlerConnection.start();

                TextMessage handlerTextMessage=handlerSession.createTextMessage();
                handlerTextMessage.setIntProperty("ProjectId",projID);
                handlerProducer.send(handlerTextMessage);
                handlerProducer.close();
            }
            catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }
            finally {
                if(handlerSession!=null)
                    handlerSession.close();
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        finally {
            if(handlerConnection!=null)
                try {
                    handlerConnection.close();
                }catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                }
        }
    }

    public static TopicSender getInstance()
    {
        return new TopicSender(CommonVariable.getTOPIC_ADDR(),CommonVariable.getTOPIC_NAME(),CommonVariable.getTOPIC_UID(),CommonVariable.getTOPIC_PWD());
    }

    public static TopicSender getInstance(String topicName)
    {
        return new TopicSender(CommonVariable.getTOPIC_ADDR(),topicName,CommonVariable.getTOPIC_UID(),CommonVariable.getTOPIC_PWD());
    }

}

package init.config.tests;

import commonUtil.CommonVariable;
import data.common.WarnLogMsg;
import data.proj.TbWarnLog;
import init.config.TopicSender;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/10/11.
 */
public class TopicSenderTest extends TestCase {
    @Test
    public void testSender()
    {
        TopicSender sender=TopicSender.getInstance();
        WarnLogMsg msg=WarnLogMsg.getDebugWarnLogMsg();
        sender.sendWarnLog(msg);
    }

    @Test
    public void testSenderHandler()
    {
        TopicSender sender=TopicSender.getInstance(CommonVariable.getHandlerWarnTopicName());
        sender.sendWarnHandlerMsg(1);
    }
}

package init.config.tests;

import commonUtil.CommonVariable;
import data.common.WarnLogMsg;
import init.config.TopicReceiver;
import init.config.TopicSender;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/10/11.
 */
public class TopicReceiverTest extends TestCase {
    @Test
    public void testStart() {
        TopicReceiver receiver = new TopicReceiver(CommonVariable.getTOPIC_ADDR(), CommonVariable.getTOPIC_NAME(),
                CommonVariable.getTOPIC_UID(), CommonVariable.getTOPIC_PWD());
        receiver.start();
    }

    public void testSendAndReceiver() throws Exception
    {
        TopicReceiver receiver = new TopicReceiver(CommonVariable.getTOPIC_ADDR(), CommonVariable.getTOPIC_NAME(),
                CommonVariable.getTOPIC_UID(), CommonVariable.getTOPIC_PWD());
        receiver.start();

        Thread.sleep(5*1000);

        TopicSender sender=TopicSender.getInstance();
        WarnLogMsg msg=WarnLogMsg.getDebugWarnLogMsg();
        sender.sendWarnLog(msg);

        Thread.sleep(10*10*1000);

    }

}

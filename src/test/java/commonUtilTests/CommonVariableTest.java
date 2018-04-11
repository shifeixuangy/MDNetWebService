package commonUtilTests;

import commonUtil.CommonVariable;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/4/12.
 */
public class CommonVariableTest extends TestCase {
    @Test
    public void testGetKeyExpireTime()
    {
        int interval= CommonVariable.getKeyExpireInterval();
        Assert.assertEquals(20,interval);
    }
    @Test
    public void testGetKeyCheckInterval()
    {
        int interval=CommonVariable.getKeyCheckInterval();
        Assert.assertEquals(200,interval);
    }

}

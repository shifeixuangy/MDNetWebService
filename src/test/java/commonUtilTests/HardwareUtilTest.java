package commonUtilTests;

import commonUtil.HardwareUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by liudongdong on 2016/4/5.
 */
public class HardwareUtilTest extends TestCase {
    @Test
    public void testGetLocalMacAddress()
    {
        Properties p= System.getProperties();
        String mac= HardwareUtil.getLocalMacAddress();
        String str="";
    }

    @Test
    public void testGetSerial()
    {
        String serial=HardwareUtil.getMotherboardSerial();
        String str="";
    }
}

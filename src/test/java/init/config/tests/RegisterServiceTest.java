package init.config.tests;

import init.config.RegisterService;
import junit.framework.TestCase;

/**
 * Created by Administrator on 2016/4/7.
 */
public class RegisterServiceTest extends TestCase
{
    public void testStart()
    {
        RegisterService rs=new RegisterService();
        rs.start();
    }

    public void testInit() throws  Exception
    {
        RegisterService.init();
        System.in.read();
    }
}

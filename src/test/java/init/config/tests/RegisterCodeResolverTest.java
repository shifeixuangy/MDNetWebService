package init.config.tests;

import init.config.RegisterService;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2016/4/13.
 */
public class RegisterCodeResolverTest extends TestCase {
    @Test
    public void testResolver()
    {
        String register="F91B0D02560857B3EB1EDFB01628CC73239E3BB3D7947D9CDD994E28DF35979E";
        RegisterService.RegisterCodeResolver resolver=new RegisterService.RegisterCodeResolver(register);
        String mac=resolver.getMac();
        LocalDateTime outTime=resolver.getOutTime();
        String str="";
    }
}

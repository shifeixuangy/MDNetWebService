package dataDAOTests;

import commonUtil.HardwareUtil;
import dataDAO.register.RegisterOperator;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class RegisterOperatorTest extends TestCase {
    @Test
    public void testRegister()
    {
        HardwareUtil.getAllLocalMacs().forEach(System.out::println);
        RegisterOperator ro=new RegisterOperator();
        ro.writeValue("netmonitor_mac","dfdfddfff");
        ro.writeValue("netmonitor_register","dfdfdzxc");
        assert "dfdfddfff".equals( ro.readValue("netmonitor_mac"));
    }
}

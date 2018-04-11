package commonUtilTests;

import commonUtil.DBUtil;
import commonUtil.JDBCUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by liudongdong on 2015/12/24.
 */
public class JDBCUtilTest extends TestCase {
    @Test
    public void testGetConnection()
    {
        DBUtil.testInit();
        Connection c= JDBCUtil.getJDBCConnection(1);
        String str="";
    }
}

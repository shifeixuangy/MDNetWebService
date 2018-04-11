package commonUtilTests;

import commonUtil.DBConfig;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/8/18.
 */
public class DBConfigTest extends TestCase {
    @Test
    public void testTT()
    {
        DBConfig dbc= DBConfig.getCommonConfig();
        String mysql=dbc.getMySqlString();
        String sString=dbc.getDBStorageString();
        String projName=DBConfig.getPROJ_DB_NAME();
        String t="";
    }
}

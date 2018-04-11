package commonUtilTests;

import commonUtil.ConnectionStringExtract;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/4/18.
 */
public class ConnectionStringExtractTest extends TestCase {
    private String connString;

    @Before
    public void setUp()
    {
        connString="server=localhost;user id=sa;password=420529;database=mdm_as_db";
    }

    @Test
    public void testExtract()
    {
        //jdbc:mysql://localhost:3306/mdm_common_db
        ConnectionStringExtract extract=new ConnectionStringExtract(connString);
        Assert.assertEquals("localhost", extract.getServer());
        Assert.assertEquals("mdm_as_db",extract.getDbName());
        Assert.assertEquals("sa",extract.getUid());
        Assert.assertEquals("420529",extract.getPwd());
        Assert.assertEquals("jdbc:mysql://localhost:3306/mdm_as_db",extract.getMySqlConnUrl());
    }
}

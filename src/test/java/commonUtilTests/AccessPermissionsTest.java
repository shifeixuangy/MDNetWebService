package commonUtilTests;

import commonUtil.AccessPermissions;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/23.
 */
public class AccessPermissionsTest extends TestCase {

    @Test
    public void testGetLevelCount()
    {
        Assert.assertTrue(AccessPermissions.getLevelCount()==3);
    }


    @Test
    public void testGetLevelPermissions()
    {

        AccessPermissions.getLevelPermissions(10);
        String s="";


    }


}

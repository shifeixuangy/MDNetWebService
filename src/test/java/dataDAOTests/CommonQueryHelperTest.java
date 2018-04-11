package dataDAOTests;

import data.common.TbProjManager;
import data.common.TbUser;
import dataDAO.CommonQueryHelper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/4/27.
 */
public class CommonQueryHelperTest extends TestCase {
    @Test
    public void testGetUser()
    {
        TbUser user= CommonQueryHelper.getUser("admin","admin");
        Assert.assertFalse(user==null);
    }

    @Test
    public void testGetUserProjManager()
    {
        TbUser user= CommonQueryHelper.getUser("admin","admin");
        TbProjManager manager=CommonQueryHelper.getUserProjManager(user.getId());
        Assert.assertFalse(manager==null);
    }

}

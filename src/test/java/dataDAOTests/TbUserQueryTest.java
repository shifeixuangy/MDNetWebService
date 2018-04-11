package dataDAOTests;

import commonUtil.CommonVariable;
import data.common.TbUser;
import dataDAO.impl.TbUserQuery;
import dataDAO.interfaces.ObjectsQuery;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbUserQueryTest  extends TestCase{
    @Test
    public void testQuery()
    {
        ObjectsQuery<TbUser>userQuery=new TbUserQuery();
        Assert.assertTrue( userQuery.query(null, CommonVariable.DEFAULT_PROJID).size()==1);
    }

}

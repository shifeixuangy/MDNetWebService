package dataDAOTests;

import commonUtil.CommonVariable;
import data.common.TbProjManager;
import dataDAO.impl.TbProjManagerQuery;
import dataDAO.interfaces.ObjectsQuery;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class TbProjManagerTest extends TestCase {
    @Test
    public void testQuery()
    {
        ObjectsQuery<TbProjManager>mQuery=new TbProjManagerQuery();
        List<TbProjManager>results= mQuery.query(null, CommonVariable.DEFAULT_PROJID);
        Assert.assertTrue(results.size()>=1);
    }
}

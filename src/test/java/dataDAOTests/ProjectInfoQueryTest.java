package dataDAOTests;

import commonUtil.CommonVariable;
import data.common.TbProjInfo;
import dataDAO.impl.ProjectInfoQuery;
import dataDAO.interfaces.ObjectsQuery;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/19.
 */
public class ProjectInfoQueryTest extends TestCase {

    @Test
    public void testProjectInfoQuery()
    {
        ObjectsQuery<TbProjInfo>query=new ProjectInfoQuery();
        List<TbProjInfo>results= query.query(null, CommonVariable.DEFAULT_PROJID);
        Assert.assertTrue(results.size()>=1);
    }


}

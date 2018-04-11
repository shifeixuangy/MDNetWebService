package dataDAOTests;

//import data.TbDbAttri;
import commonUtil.CommonVariable;
import data.common.TbLog;
import data.common.TbProjInfo;
import dataDAO.ObjectOperation;
import dataDAO.ObjectOperationImpl;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by liudongdong on 2015/4/11.
 */
public class ObjectOperationImplTest extends TestCase {
    private ObjectOperation objectOperation=null;
    private Integer autoId=-1;

    @Before
    public void setUp()
    {
        objectOperation=new ObjectOperationImpl();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        TbLog log=new TbLog();
        log.setId(autoId);
        objectOperation.deleteObject(log,CommonVariable.DEFAULT_PROJID);
        super.tearDown();
    }

    @Test
    public void testAddObject()
    {
        TbLog log=new TbLog();
        log.setProjId(CommonVariable.DEFAULT_PROJID);
        log.setLogMsg("tianjiaceshi");
        log.setLogType(Short.parseShort("1"));
        log.setTime(Timestamp.valueOf(LocalDateTime.now()));
        autoId=objectOperation.addObject(log,CommonVariable.DEFAULT_PROJID);
        Assert.assertTrue(autoId>=0);
    }
    @Test
    public void testAddProjInfo()
    {
        TbProjInfo pi=new TbProjInfo();
        pi.setId(100);
        pi.setName("suibiankan");
        new ObjectOperationImpl().addObject(pi,CommonVariable.DEFAULT_PROJID);
        String s="";
    }

}

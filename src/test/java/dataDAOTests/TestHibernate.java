package dataDAOTests;

import data.proj.TbWarnValue;
import dataDAO.DataHelper;
import junit.framework.TestCase;

/**
 * Created by liudongdong on 2015/7/10.
 */
public class TestHibernate extends TestCase {
    public void testChinese()
    {
        TbWarnValue wv=new TbWarnValue();
        wv.setValid(true);
        wv.setValue(10);
        wv.setWarnLevel((short)1);
        wv.setSensorType(0);
        wv.setWarnType("这就另一个中午");
        DataHelper.add(wv,0);
    }
}

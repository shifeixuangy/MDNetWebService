package dataDAOTests;

import data.proj.TbTpsData;
import dataDAO.proj.impl.TPSOperation;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/12/20.
 */
public class TPSOperationTest extends TestCase {
    @Test
    public void testAddTPSData()
    {
        TPSOperation o=new TPSOperation();
        TbTpsData data=new TbTpsData();
        data.setSensorId(8);
        data.setTime(Timestamp.valueOf("2015-01-01 00:00:00"));
        data.setDispX(1);
        o.addTPSData(1,data,true);
    }
}

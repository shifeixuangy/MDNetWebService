package dataDAOTests;

import data.proj.TbDs2Data;
import data.proj.TbTpsData;
import data.request.parameters.TPSData;
import dataDAO.proj.impl.DS2Query;
import dataDAO.proj.impl.TPSQuery;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/16.
 */
public class TPSQueryTest extends TestCase {
    @Test
    public void testGetMinData()
    {
        DS2Query tq=new DS2Query();
        List<TbDs2Data>datas= tq.getMinTimeData(1,-1);
        int i=datas.size();
    }

    @Test
    public void testGetData()
    {
        TPSQuery tq=new TPSQuery();
        List<TbTpsData>datas=tq.getTpsData(1,8, Timestamp.valueOf("2014-01-01 00:00:00"),Timestamp.valueOf("2016-01-01 00:00:00"));
        String str="";
    }
}

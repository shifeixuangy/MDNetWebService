package dataDAOTests;

import data.common.QueryDensity;
import data.common.SensorType;
import data.proj.SensorData;
import data.proj.TbWtData;
import dataDAO.proj.impl.SensorDataQueryImpl;
import dataDAO.proj.interfaces.SensorDataQuery;
import dataDAO.proj.interfaces.SensorNewDataQuery;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/30.
 */
public class SensorDataQueryImplTest extends TestCase {
    private Timestamp maxTime=Timestamp.valueOf(LocalDateTime.now());
    private Timestamp minTime=Timestamp.valueOf("2015-02-12 13:59:52");
    @Test
    public void testGetWtAll()
    {
        //'2015-03-27 18:00:33' max
        //'2015-02-13 13:59:52'
        SensorDataQuery<TbWtData>wtQuery=new SensorDataQueryImpl<>(0, SensorType.TYPE_WATER);
        List<TbWtData>wts=wtQuery.getData(minTime,maxTime, QueryDensity.Normal,null);
        Assert.assertEquals(wts.size(),13439);
    }

    @Test
    public void testGetWtHour()
    {
        SensorDataQuery<TbWtData>wtQuery=new SensorDataQueryImpl<>(0, SensorType.TYPE_WATER);
        List<TbWtData>wts=wtQuery.getData(minTime,maxTime, QueryDensity.Hour,null);
        Assert.assertEquals(wts.size(),43);
    }

    @Test
    public void testGetWtDay()
    {
        SensorDataQuery<TbWtData>wtQuery=new SensorDataQueryImpl<>(0, SensorType.TYPE_WATER);
        List<TbWtData>wts=wtQuery.getData(minTime,maxTime, QueryDensity.Day,null);
        Assert.assertEquals(wts.size(),10);
    }


    @Test
    public void testGetNewData()
    {
        SensorNewDataQuery sQuery=new SensorDataQueryImpl<>(0,SensorType.TYPE_GPS);
//        SensorData data= sQuery.getNewData(1);


        List<Integer>sids= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<SensorData>datas= sQuery.getNewData(sids);

        String s="";
    }


}

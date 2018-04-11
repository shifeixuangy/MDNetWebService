package dataDAOTests;

import data.common.QueryDensity;
import data.common.SensorType;
import data.proj.TbMpData;
import dataDAO.proj.impl.SensorDataQueryImpl;
import dataDAO.proj.interfaces.SensorDataQuery;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/30.
 */
public class GPSDataQueryTest extends TestCase {
    private Timestamp maxTime=Timestamp.valueOf(LocalDateTime.now());
    //'2015-01-24 10:16:44'
    private Timestamp minTime=Timestamp.valueOf("2015-01-23 10:16:44");

    @Test
    public void testGetAll()
    {
        SensorDataQuery<TbMpData>mpQuery=new SensorDataQueryImpl<TbMpData>(0, SensorType.TYPE_GPS);
        List<TbMpData>mps= mpQuery.getData(minTime, maxTime, QueryDensity.Normal, null);
        Assert.assertEquals(mps.size(),110385);
    }

    @Test
    public void testGetHour()
    {
        SensorDataQuery<TbMpData>mpQuery=new SensorDataQueryImpl<TbMpData>(0, SensorType.TYPE_GPS);
        List<Integer>ids= Arrays.asList(7,9,10);
        List<TbMpData>mps= mpQuery.getData(minTime, maxTime, QueryDensity.Hour, ids);
        Assert.assertEquals(mps.size(),248);
    }



}

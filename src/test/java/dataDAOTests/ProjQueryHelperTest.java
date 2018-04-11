package dataDAOTests;

import data.common.SensorType;
import data.response.results.SensorResult;
import dataDAO.ProjQueryHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/25.
 */
public class ProjQueryHelperTest extends TestCase {
    @Test
    public void testGetSensorResult()
    {
        List<SensorResult>results= ProjQueryHelper.getSensorResults(0, SensorType.All);
        List<SensorResult>results1=ProjQueryHelper.getSensorResults(0,SensorType.TYPE_GPS);
        String s="";
    }
}

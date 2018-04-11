package commonUtilTests;

import commonUtil.SensorDataSelects;
import data.common.SensorType;
import data.proj.TbDbData;
import data.proj.TbWtData;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/4/30.
 */
public class SensorDataSelectsTest extends TestCase {
    @Test
    public void testGetSensorSelect()
    {
        String select= SensorDataSelects.getSensorSelect(SensorType.TYPE_WATER);
        Assert.assertEquals(select,"SensorID, min(`Time`) as `Time`, avg(Stage) as Stage");
    }

    @Test
    public void testGetSensorTable()
    {
        String table=SensorDataSelects.getSensorTable(SensorType.TYPE_WATER);
        Assert.assertEquals(table,"tb_wt_data");
    }

    @Test
    public void testGetClass() throws Exception
    {
        Class c=Class.forName("data.proj.TbDbData");
        TbDbData db=(TbDbData) c.newInstance();
        String s=c.getName();
    }

    @Test
    public void testGetSensorClass()
    {
        Assert.assertEquals(SensorDataSelects.getSensorClass(SensorType.TYPE_WATER), TbWtData.class);
    }


}

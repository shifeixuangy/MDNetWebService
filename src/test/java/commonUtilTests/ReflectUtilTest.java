package commonUtilTests;

import commonUtil.ReflectUtil;
import data.proj.TbMpData;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by liudongdong on 2016/2/18.
 */
public class ReflectUtilTest extends TestCase {
    @Test
    public void testReflect()
    {
        TbMpData mpData= (TbMpData) ReflectUtil.getDataObject(3,TbMpData.class);
        assert  mpData.getId()==3;
    }
}

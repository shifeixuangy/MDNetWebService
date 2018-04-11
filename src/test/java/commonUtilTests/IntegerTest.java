package commonUtilTests;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liudongdong on 2015/4/19.
 */
public class IntegerTest extends TestCase {
    public static interface TestInterface
    {
        public String invoke();
    }

    public static class TestBase implements TestInterface
    {
        @Override
        public String invoke() {
            return "Hello";
        }
    }

    public static class TestDrived extends TestBase
    {}


    @Test
    public void testInt()
    {
        Integer i1=5;
        Integer i2=200;
        Assert.assertTrue(i2>i1);
        String s2="";
//        boolean f=true;
    }

    @Test
    public void testImplements()
    {
        TestInterface ti=new TestDrived();
        Assert.assertTrue(ti.invoke()=="Hello");
    }

}

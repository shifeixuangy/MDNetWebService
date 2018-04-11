package commonUtilTests;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/12/30.
 */
public class TestBinary extends TestCase {
    @Test
    public void testBinary()
    {
        int i=5;
        int j=1;
        int k=i&j;
        int l=2;
        int m=i&l;
        assert (i&j)>0;
    }
}

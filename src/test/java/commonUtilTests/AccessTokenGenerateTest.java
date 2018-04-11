package commonUtilTests;

import commonUtil.AccessTokenGenerate;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/4/23.
 */
public class AccessTokenGenerateTest extends TestCase {

    @Test
    public void testGetToken()
    {
        String token1= AccessTokenGenerate.getAccessToken();
        String token2=AccessTokenGenerate.getAccessToken();
        String t="";
    }
}

package commonUtilTests;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import commonUtil.DESEncrypt;
/**
 * Created by liudongdong on 2015/4/11.
 */
public class DESEncryptTest extends TestCase {
    private static final String s="HelloWorld";
    @Test
    public void testEncrypt() throws Exception
    {
        String str= DESEncrypt.Encrypt(s);
        String s="";
    }
    @Test
    public void testDecrypt() throws Exception
    {
        Assert.assertEquals(s, DESEncrypt.Decrypt("52GRqQ+pHZenj02DpBWJ1Q=="));
    }
    @Test
    public void testAll() throws Exception
    {
        Assert.assertEquals(s,DESEncrypt.Decrypt(DESEncrypt.Encrypt(s)));
    }
}

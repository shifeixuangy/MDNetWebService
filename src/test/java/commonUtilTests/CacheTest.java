package commonUtilTests;

import jersey.repackaged.com.google.common.cache.CacheBuilder;
import jersey.repackaged.com.google.common.cache.CacheLoader;
import jersey.repackaged.com.google.common.cache.LoadingCache;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.internal.util.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class CacheTest extends TestCase  {
    private static Log logger= LogFactory.getLog(CacheTest.class);
    @Test
    public void testCacheBuilder() throws Exception
    {
        final LoadingCache<String,String>cacheLoader= CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key + "i come from memory"+ LocalDateTime.now().toString();
            }
        });

        new Thread(()->{
            while (true) {
                logger.info(cacheLoader.getUnchecked("key1"));
                try {
                    Thread.sleep(5 * 1000);
                }
                catch (Exception ex)
                {}
            }
        }).start();

        Thread.sleep(100*1000);
        String k1Value= cacheLoader.getUnchecked("key1");

//        Assert.assertEquals("key1i come from memory",k1Value);

    }

    @Test
    public void testInteger()
    {
        List<Integer>ilist=new LinkedList<>();
        ilist.add(new Integer(1000));
        ilist.add(new Integer(100000));
        Integer i=new Integer(1000);
        Assert.assertTrue(ilist.contains(i));
    }
    @Test
    public void testFormat() throws Exception
    {
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String str1="2010-11-11 13:13:13";
        String str2="2010/11/11 13:13:13";

        Timestamp t1=new Timestamp(sdf1.parse(str1).getTime());
        Timestamp t2=new Timestamp(sdf2.parse(str2).getTime());
        String s="";
    }

    @Test
    public void testBase64()
    {
        String str= Base64.encodeAsString(new byte[]{1,2,3,4,5});
        int i=str.length();
    }

}

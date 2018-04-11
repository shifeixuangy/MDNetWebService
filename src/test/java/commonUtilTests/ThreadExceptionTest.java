package commonUtilTests;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * Created by liudongdong on 2015/10/11.
 */
public class ThreadExceptionTest extends TestCase {
    @Test
    public void testThreadException() throws  Exception
    {
        try
        {
            Executors.newCachedThreadPool().execute(()->{
                throw new RuntimeException("线程内部异常");
            });
        }
        catch (Exception ex)
        {
            String message=ex.getMessage();
            int size=message.length();
        }

        Thread.sleep(10*1000);
    }
}

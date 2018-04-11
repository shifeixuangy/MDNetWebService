package dataDAOTests;

import data.proj.Check;
import dataDAO.DataHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by liudongdong on 2015/11/5.
 */
public class CheckTest extends TestCase {
    @Test
    public void testAddCheck()
    {
        Check c=new Check();
        c.setUploadUserID(1);
        c.setTime(Timestamp.valueOf(LocalDateTime.now()));
        c.setRemark("test");
        DataHelper.add(c,3);
    }
}

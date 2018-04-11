package servicestests;

import data.request.parameters.GetLogListParameter;
import junit.framework.TestCase;
import org.junit.Test;
import services.LogService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by liudongdong on 2015/5/24.
 */
public class LogServiceTest extends TestCase {
    @Test
    public void testGetLogList()
    {
        GetLogListParameter pa=new GetLogListParameter();
        pa.setProjID(-1);
        pa.setUid(-1);
        pa.setPageSize(100);
        pa.setPage(1);
        pa.setStartTime(Timestamp.valueOf(LocalDateTime.now().minusYears(2)));
        pa.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        LogService logService=new LogService();
        logService.getLogList(pa);
        String s="";
    }
}

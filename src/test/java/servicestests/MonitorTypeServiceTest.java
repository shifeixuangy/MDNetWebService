package servicestests;

import data.request.parameters.GetMonitorTypeListParameter;
import data.response.results.GetMonitorTypeListResult;
import junit.framework.TestCase;
import org.junit.Test;
import services.MonitorTypeService;

import java.util.List;

/**
 * Created by liudongdong on 2016/3/8.
 */
public class MonitorTypeServiceTest extends TestCase {
    @Test
    public void testGetMonitorType()
    {
        MonitorTypeService mts=new MonitorTypeService();
        GetMonitorTypeListParameter pa=new GetMonitorTypeListParameter();
        pa.setProjID(1);
        pa.setStatus(1);
        List<GetMonitorTypeListResult>results= mts.getMonitorTypeList(pa);
        int i=results.size();
    }
}

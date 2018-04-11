package servicestests;

import data.request.parameters.AddTPSDataRequestParameter;
import data.request.parameters.TPSConfig;
import data.request.parameters.TPSData;
import data.request.parameters.TPSDataWrapper;
import junit.framework.TestCase;
import org.junit.Test;
import services.TPSService;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/16.
 */
public class TPSServiceTest extends TestCase {
    @Test
    public void testAddData()
    {
        AddTPSDataRequestParameter pa=new AddTPSDataRequestParameter();
        pa.setProjID(1);
        List<TPSDataWrapper>datas=new LinkedList<>();
        pa.setDatas(datas);
        
        TPSDataWrapper dataWrapper=new TPSDataWrapper();
        datas.add(dataWrapper);

        TPSConfig config=new TPSConfig("tps1",null);
        TPSData d1=new TPSData();
        d1.setTime(Timestamp.valueOf("2015-01-01 00:00:00"));
        d1.setX(1);
        d1.setY(1);
        d1.setH(1);
        TPSData d2=new TPSData();
        d2.setTime(Timestamp.valueOf("2015-01-02 00:00:00"));
        d2.setX(2);
        d2.setY(2);
        d2.setH(2);
        dataWrapper.setDatas(Arrays.asList(d1,d2));
        dataWrapper.setConfig(config);

        TPSService ts=new TPSService();
        ts.adddTPSData(pa);
        String str="";
    }
}

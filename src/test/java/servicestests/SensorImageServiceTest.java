package servicestests;

import data.request.parameters.GetSensorImageParameter;
import data.response.results.SensorImageWrapper;
import junit.framework.TestCase;
import services.SensorImageService;

import java.util.List;

/**
 * Created by liudongdong on 2016/1/17.
 */
public class SensorImageServiceTest extends TestCase {
    public void testGetSensorImage()
    {
        GetSensorImageParameter pa=new GetSensorImageParameter();
        pa.setSensorID(9);
        pa.setProjID(1);
        pa.setImageNumber(1);
        SensorImageService sis=new SensorImageService();
        List<SensorImageWrapper> result= sis.getSensorImage(pa);
        String str="";
    }
}

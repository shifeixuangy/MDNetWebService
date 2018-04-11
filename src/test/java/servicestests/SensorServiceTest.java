package servicestests;

import data.common.QueryDensity;
import data.common.SensorType;
import data.request.parameters.GetSensorDataRecordParameter;
import data.request.parameters.GetSensorExtremeDataParameter;
import data.request.parameters.GetSensorListParameter;
import data.request.parameters.GetSensorParameter;
import data.response.results.GetSensorExtremeDataResult;
import data.response.results.SensorResult;
import junit.framework.TestCase;
import org.junit.Test;
import services.SensorServices;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/24.
 */
public class SensorServiceTest extends TestCase{
    private SensorServices ss=new SensorServices();
    public void testGetSensor()
    {
        GetSensorParameter pa=new GetSensorParameter();
        pa.setProjID(0);
        pa.setSensorID(1);
        SensorResult sr= ss.getSensor(pa);
        String s="";
    }

    @Test
    public void testGetSensorData()
    {
        GetSensorDataRecordParameter pa=new GetSensorDataRecordParameter();
        pa.setProjID(1);
        pa.setSensorType(18);
        pa.setSensorID(2);
        pa.setDensity(QueryDensity.Normal.toInt());
        pa.setStartTime(Timestamp.valueOf("2016-01-01 00:00:00"));
        pa.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        List<Object> result= ss.getSensorDataRecord(pa);
        String str="";
    }

    @Test
    public void testGetSensorList()
    {
        GetSensorListParameter pa=new GetSensorListParameter();
        pa.setProjID(1);
        pa.setSensorType(SensorType.TYPE_GPS.toInt());
        List<SensorResult>result= ss.getSensorList(pa);
        String str="";
    }

    public void testGetTps2Data()
    {

    }

    @Test
    public void testGetSensorExtrmeData()
    {
        GetSensorExtremeDataParameter pa=new GetSensorExtremeDataParameter();
        pa.setProjID(1);
        pa.setDensity(QueryDensity.Hour.toInt());
        pa.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        pa.setSensorIDs(Arrays.asList(74,75));
        pa.setSensorType(SensorType.TYPE_RAIN.toInt());
        pa.setStartTime(Timestamp.valueOf("2016-03-02 00:00:00"));
        List<GetSensorExtremeDataResult>result= ss.getsensorExtremeData(pa);
        String str="";
    }

    @Test
    public void testGetSensorExtremeDataMp()
    {
        GetSensorExtremeDataParameter pa=new GetSensorExtremeDataParameter();
        pa.setProjID(1);
        pa.setDensity(QueryDensity.Hour.toInt());
        pa.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        pa.setSensorIDs(Arrays.asList(68,69));
        pa.setSensorType(SensorType.TYPE_GPS.toInt());
        pa.setStartTime(Timestamp.valueOf("2016-03-02 00:00:00"));
        List<GetSensorExtremeDataResult>result= ss.getsensorExtremeData(pa);
        String str="";
    }
}

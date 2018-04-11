package servicestests;

import commonUtil.DBUtil;
import data.common.SensorType;
import data.proj.TbChannel;
import data.proj.TbChannelData;
import data.proj.TbSensorAttri;
import data.request.parameters.*;
import data.response.results.GetChannelFileListResult;
import dataDAO.DataHelper;
import junit.framework.TestCase;
import org.junit.Test;
import services.ChannelService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by liudongdong on 2015/12/8.
 */
public class ChannelServiceTest extends TestCase {
    private ChannelService cs=new ChannelService();
    @Test
    public  void testAddChannel()
    {
        TbSensorAttri attri=new TbSensorAttri();
        attri.setSensorType(SensorType.TYPE_SHOCK.toInt());
        attri.setName("bp2");
        int attriID= DataHelper.add(attri,1);
        TbChannel channel=new TbChannel();
        channel.setSensorID(attriID);
        channel.setChannelName("通道1");
        AddAndUpdateChannelParameter pa=new AddAndUpdateChannelParameter();
        pa.setProjID(1);
        pa.setChannel(channel);

        cs.addChannel(pa);
    }

    @Test
    public void testAddChannelData()
    {
        AddChannelDataParameter pa=new AddChannelDataParameter();
        pa.setProjID(1);
        TbChannelData cd=new TbChannelData();
        pa.setChannelData(cd);

        cd.setSensorId(1);
        cd.setFileID("f1");
        cd.setChannelID(1);
        cd.setTime(Timestamp.valueOf("2015-12-01 00:00:00"));
        cd.setData("[3,4,5]");
        Integer t= cs.addChannelData(pa);
        String s="";
    }

    @Test
    public void testGetData()
    {
        DBUtil.testInit();
        GetChannelDataListParameter pa=new GetChannelDataListParameter();
        pa.setProjID(1);
        pa.setSensorID(7);
        pa.setChannelID(2);
        pa.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        pa.setBeginTime(Timestamp.valueOf("2014-01-01 00:00:00"));

        List<TbChannelData>ds=cs.getChannelDataList(pa);
        String s="";
    }

    @Test
    public void testAddData()
    {
        AddDataParameter pa=new AddDataParameter();
        pa.setProjID(1);
        TbSensorAttri sa=new TbSensorAttri();
        sa.setName("shock1");
        sa.setSensorType(SensorType.TYPE_SHOCK.toInt());
        sa.setIsValid(true);
        pa.setSensor(sa);

        ChannelDataWrapper cdw=new ChannelDataWrapper();
        cdw.setFileID("file1");
        cdw.setTime(Timestamp.valueOf("2015-01-01 00:00:00"));
        TbChannel ch=new TbChannel();
        ch.setChannelName("ch1");
        cdw.setChannel(ch);
        StringBuilder chData=new StringBuilder();
        chData.append("[");
        Random rnd=new Random(System.currentTimeMillis());
        for(int i=0;i<100000;++i)
        {
            chData.append(Double.toString(rnd.nextDouble())+",");
        }
        chData.deleteCharAt(chData.lastIndexOf(","));
        chData.append("]");
        cdw.setChannelData(chData.toString());
        pa.setChannelDatas(Arrays.asList(cdw));
        long miPre=System.currentTimeMillis();
        cs.addData(pa);
        long miNow=System.currentTimeMillis();
        System.out.println(miNow-miPre);
    }

    public void testGetFileNames()
    {
        GetChannelFileListParameter pa=new GetChannelFileListParameter();
        pa.setProjID(1);
        pa.setSensorID(7);
        pa.setBeginTime(Timestamp.valueOf("2013-01-01 00:00:00"));
        pa.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        List<GetChannelFileListResult>files= cs.getChannelFileList(pa);
        String str="";
    }

    @Test
    public void testGetDataByFile()
    {
        ChannelService cs=new ChannelService();
        GetChannelDataListByFileParameter pa=new GetChannelDataListByFileParameter();
        pa.setProjID(1);
        pa.setSensorID(4);
        pa.setChannelID(1);
        pa.setFileID("f1");
        List<TbChannelData>data= cs.getChannelDataListByFile(pa);
        String s="";
    }
}

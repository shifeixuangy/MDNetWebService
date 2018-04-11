package commonUtil;

import data.common.SensorType;
import data.proj.TbMpData;
import data.proj.TbMpDataAvg;

/**
 * Created by liudongdong on 2016/3/18.
 */
public class TbMpDataUtil {
    public static void changeData(Object o)
    {
        if(o==null)
            return;
        if(o instanceof TbMpData)
        {
            TbMpData mpData=(TbMpData)o;
            mpData.setDispX(mpData.getDispX()*0.5);
            mpData.setDispY(mpData.getDispY()*0.5);
            mpData.setDispH(mpData.getDispH()*0.25);
            mpData.setVelocityX(mpData.getVelocityX()*0.5);
            mpData.setVelocityY(mpData.getVelocityY()*0.5);
            mpData.setVelocityH(mpData.getVelocityH()*0.25);
            mpData.setAccelerX(mpData.getAccelerX()*0.5);
            mpData.setAccelerY(mpData.getAccelerY()*0.5);
            mpData.setAccelerH(mpData.getAccelerH()*0.25);
        }
        if(o instanceof TbMpDataAvg)
        {
            TbMpDataAvg avgMpData=(TbMpDataAvg)o;
            avgMpData.setDispX(avgMpData.getDispX()*0.5);
            avgMpData.setDispY(avgMpData.getDispY()*0.5);
            avgMpData.setDispH(avgMpData.getDispH()*0.25);
            avgMpData.setVelocityX(avgMpData.getVelocityX()*0.5);
            avgMpData.setVelocityY(avgMpData.getVelocityY()*0.5);
            avgMpData.setVelocityH(avgMpData.getVelocityH()*0.25);
            avgMpData.setAccelerX(avgMpData.getAccelerX()*0.5);
            avgMpData.setAccelerY(avgMpData.getAccelerY()*0.5);
            avgMpData.setAccelerH(avgMpData.getAccelerH()*0.25);
        }
    }

    public static void changeData(int projID,int sensorType,Object o)
    {
        if(projID==CommonVariable.getTestProjID())
        {
            if((sensorType== SensorType.TYPE_GPS.toInt())||(sensorType==SensorType.All.toInt()))
            {
                changeData(o);
            }
        }
    }
}

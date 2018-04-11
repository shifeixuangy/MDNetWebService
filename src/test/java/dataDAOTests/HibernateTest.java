package dataDAOTests;

import commonUtil.CommonVariable;
import commonUtil.DBUtil;
import data.proj.TbMpData;
import data.proj.TbSensorAttri;
import data.proj.TbWarnLog;
import data.proj.WarnLogStatus;
import data.request.parameters.GetWarnLogListParameter;
import data.response.results.GetWarnLogListResultWarpper;
import dataDAO.ProjQueryHelper;
import dataDAO.proj.impl.NativeSqlQueryImpl;
import dataDAO.proj.impl.UniqueSqlQueryImpl;
import dataDAO.proj.interfaces.NativeSqlQuery;
import dataDAO.proj.interfaces.UniqueSqlQuery;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongdong on 2015/5/7.
 */
public class HibernateTest extends TestCase {
    @Test
    public void testGetCount()
    {
        UniqueSqlQuery<Integer>uq=new UniqueSqlQueryImpl<Integer>();
        Map<String,Type>map=new HashMap<>();
        map.put("LogCount",StandardBasicTypes.INTEGER);
        Integer result= uq.query(CommonVariable.DEFAULT_PROJID," select count(1) as LogCount from tb_proj_info ",map);
        String sql="";
    }

    @Test
    public void test2()
    {
        GetWarnLogListParameter pa=new GetWarnLogListParameter();
        pa.setPage(1);
        pa.setPageSize(10);
        pa.setDealed(WarnLogStatus.ALL);
        pa.setStartTime(Timestamp.valueOf("2014-01-01 00:00:00"));
        pa.setEndTime(Timestamp.valueOf("2016-01-01 00:00:00"));
        pa.setSensorID(1);
        pa.setProjID(0);
        GetWarnLogListResultWarpper resut= ProjQueryHelper.getWarnLogList(pa);
        String s="";
    }

    @Test
    public void testPage()
    {
        Session session=DBUtil.getSession(0);
        session.beginTransaction();
         List<TbWarnLog>logs= session.createQuery("from data.proj.TbWarnLog order by Time asc ").setMaxResults(2).list();
        String str="";
    }
    @Test
    public void testInt()
    {
        int i=(3-1)/5+1;
        Assert.assertTrue(i==1);
    }

    @Test
    public void testtt()
    {
        String sql="select ID,SensorID,min(Time) as Time,avg(Disp_X) as Disp_X,avg(Disp_Y) as Disp_Y,avg(Disp_H) as Disp_H,avg(Velocity_X) as Velocity_X,avg(Velocity_Y) as Velocity_Y,avg(Velocity_H) as Velocity_H,avg(Acceler_X) as Acceler_X,avg(Acceler_Y) as Acceler_Y,avg(Acceler_H) as Acceler_H,avg(Azimuth) as Azimuth  from     tb_mp_data     where  SensorID in (1) and  Time between '2012-01-01 00:00:00.0' and '2016-01-01 00:00:00.0'  group by SensorID,timestampdiff(hour,Time,'2015-01-01 00:00:00')  ";
        NativeSqlQuery<TbMpData>mQuery=new NativeSqlQueryImpl<>();
        List<TbMpData>datas= mQuery.query(0,sql,TbMpData.class);
        String s="";
    }

    @Test
    public void testtt2()
    {
        String sql="select * from tb_sensor_attri";
        NativeSqlQuery<TbSensorAttri>attri=new NativeSqlQueryImpl<>();
        List<TbSensorAttri>attris= attri.query(0,sql,TbSensorAttri.class);
        String s="";

    }


}

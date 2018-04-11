package commonUtilTests;

import commonUtil.CreateDB;
import data.common.TbProjInfo;
import data.proj.TbMonitorType;
import dataDAO.DataHelper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import services.ProjInfoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * Created by liudongdong on 2015/8/17.
 */
public class CreateDBTest extends TestCase{
    @Test
    public void testGetDBString()
    {
        String s=CreateDB.getCreateDBString("helloworld");
        String s2="";

    }
    @Test
    public void testIsDBExists()
    {
//        boolean f= CreateDB.isDBExists("mdm_as_db");
//        Assert.assertTrue(f);
//        boolean f2=CreateDB.isDBExists("dfdfdf");
//        Assert.assertFalse(f2);
    }

    @Test
    public void testAddProjInfo()
    {
        TbProjInfo pi=new TbProjInfo();
        pi.setName("wojiukan");
        pi.setConnectString("server=localhost;user id=root;password=420529;database=wojiukankan");
        ProjInfoService pis=new ProjInfoService();
        pis.addProjInfo(pi);

    }
    @Test
    public void testGetStringList()
    {
        String dbName="df";
        List<String> result=CreateDB.getSqlStrings(dbName);
        String str="";
        try {
            Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&amp;characterEncoding=UTF-8", "root", "420529");
           conn.setAutoCommit(false);
            Statement stmt=conn.createStatement();
            result.forEach(s->{
                try {
                    stmt.addBatch(s);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace(System.out);
                }
            });
            stmt.executeBatch();
            conn.commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }

    @Test
    public void testAddMonitorType()
    {
        TbMonitorType.defaultMonitorTypes().forEach(tm->{
            DataHelper.add(tm,73);
        });
    }


}

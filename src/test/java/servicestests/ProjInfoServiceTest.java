package servicestests;

import data.common.TbProjInfo;
import data.proj.DBInfo;
import dataDAO.DataHelper;
import junit.framework.TestCase;
import org.junit.Test;
import services.ProjInfoService;

/**
 * Created by liudongdong on 2016/3/10.
 */
public class ProjInfoServiceTest extends TestCase{
    @Test
    public void testAddProj()
    {
        ProjInfoService pis=new ProjInfoService();
        TbProjInfo pi=new TbProjInfo();
        pi.setName("wojiukank");
        pis.addProjInfo(pi);
    }

    @Test
    public void testAddDBVersion()
    {
        DBInfo di1=new DBInfo();
        di1.setDBVersion("di1");
        DBInfo di2=new DBInfo();
        di2.setDBVersion("di2");
        DataHelper.addWithoutKey(di1,1);
        DataHelper.addWithoutKey(di2,1);
    }
}

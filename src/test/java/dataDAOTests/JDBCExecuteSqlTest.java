package dataDAOTests;

import commonUtil.DBUtil;
import data.proj.TbChannelData;
import dataDAO.DataHelper;
import dataDAO.JDBCExecuteSql;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by liudongdong on 2015/12/24.
 */
public class JDBCExecuteSqlTest extends TestCase {
    @Test
    public void testGetChannelData()
    {
        DBUtil.testInit();
        String sql=" select * from tb_channel_data ";
        List<TbChannelData>datas= JDBCExecuteSql.getChannelData(1,sql);
        String str="";
    }

    @Test
    public void testDeleteDataBase()
    {
        DataHelper.deleteDatabase("helloworld");
    }
}

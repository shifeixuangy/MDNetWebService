package dataDAOTests;

import commonUtil.CommonVariable;
import data.common.TbUser;
import data.proj.TbImageMeta;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.query.helper.TbImageMetaQueryHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by liudongdong on 2015/6/18.
 */
public class CacheTest extends TestCase {
    @Test
    public void testUser()
    {
        TbUser user= CommonQueryHelper.getUser("admin","admin");
        TbUser newUser=new TbUser();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        newUser.setAccount("test"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        newUser.setPassword("test");
        DataHelper.add(newUser, CommonVariable.DEFAULT_PROJID);
        TbUser user2= CommonQueryHelper.getUser("admin","admin");
        String s="";
    }

    @Test
    public void testGetImageMeta()
    {
        List<TbImageMeta>images= TbImageMetaQueryHelper.getImages(1,0,true);
        String s="";
    }
}

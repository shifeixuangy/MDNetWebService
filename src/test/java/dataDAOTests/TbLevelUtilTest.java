package dataDAOTests;

import dataDAO.TbLevelUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by liudongdong on 2016/3/22.
 */
public class TbLevelUtilTest extends TestCase {
    @Test
    public void testGetSubIDs()
    {
        List<Integer>ids= TbLevelUtil.getSubIDs(1);
        String str="";
    }
}

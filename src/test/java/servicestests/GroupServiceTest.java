package servicestests;

import data.proj.TbGroup;
import junit.framework.TestCase;
import org.junit.Test;
import services.GroupService;

import java.util.List;

/**
 * Created by admin on 2016/5/23.
 */
public class GroupServiceTest extends TestCase {
    @Test
    public void testGetGroup()
    {
        int i="1".compareTo(null);

    }

    @Test
    public void testGetGroupList()
    {
        GroupService gs=new GroupService();
        List<TbGroup>groups= gs.getGroupList(1);
        String str="";
    }
}

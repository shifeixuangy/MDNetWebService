package servicestests;

import data.common.TbUser;
import data.request.parameters.AddUserInfoParameter;
import junit.framework.TestCase;
import org.junit.Test;
import services.AdminService;

/**
 * Created by liudongdong on 2015/7/10.
 */
public class AdminServiceTest extends TestCase {
    private AdminService as=new AdminService();
    @Test
    public void testAddUserInfo()
    {
        AddUserInfoParameter pa=new AddUserInfoParameter();
        pa.setAccount("tt");
        pa.setPassword("tt");
        pa.setConfirmPassword("tt");
        as.addUserInfo(pa);
        String s="";
    }
}

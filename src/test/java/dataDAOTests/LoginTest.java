package dataDAOTests;

import data.common.AccessType;
import junit.framework.TestCase;
import org.junit.Test;
import services.CommonService;

/**
 * Created by liudongdong on 2015/6/18.
 */
public class LoginTest extends TestCase {
    @Test
    public void testLogin()throws Exception
    {
        CommonService commonService=new CommonService();
        String admin="admin";
        int i=100;
        while (i>0) {
            String token = commonService.signIn(admin,admin, AccessType.WEB);
//            commonService.signOut(token);
            String token2=commonService.signIn(admin,admin,AccessType.ANDROID);
            commonService.signOut(token);
            Thread.sleep(5*1000);
            i--;
        }
        String s="";

    }
}

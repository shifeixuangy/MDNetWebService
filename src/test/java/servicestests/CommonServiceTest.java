package servicestests;

import commonUtil.CommonVariable;
import commonUtil.ImageUtil;
import data.common.AccessType;
import data.request.parameters.SetUserHeadPhotoParameter;
import junit.framework.TestCase;
import org.junit.Test;
import services.CommonService;

import java.util.Base64;

/**
 * Created by liudongdong on 2015/4/28.
 */
public class CommonServiceTest extends TestCase {
    @Test
    public void testSetUserPhoto()
    {
        ImageUtil.setRootPath("F:\\java\\javaproject\\MedoService\\target\\MedoService\\");
        SetUserHeadPhotoParameter shp=new SetUserHeadPhotoParameter();
        shp.setUserID(1);
        shp.setPhotoName("test.jpg");
        byte[]imgContent=new byte[]{1,2};
        shp.setPhotoContent( Base64.getEncoder().encodeToString(imgContent));
        CommonService cs=new CommonService();
        String accessToken= cs.signIn("admin","admin", AccessType.WEB);
        cs.setUserHeadPhoto(shp,accessToken);
        String str="";
    }
}

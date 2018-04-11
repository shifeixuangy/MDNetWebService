package commonUtilTests;

import commonUtil.AppConfig;
import commonUtil.CommonVariable;
import data.common.AccessType;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liudongdong on 2016/1/17.
 */
public class AppConfigTest extends TestCase {
    @Test
    public void testAppName()
    {
        String str2=AccessType.ANDROID.toString();
        CommonVariable.setRootPath("F:\\java\\javaproject\\MedoService\\target\\MedoService\\");
        String s= AppConfig.getAppName(AccessType.ANDROID);
        String str="";
//        String s=AppConfig.getAndroidBG();
//        String appName=AppConfig.getAppName();
//        assert AppConfig.getAndroidBG().equals("default");
//        AppConfig.setAndroidBG("testandroid");
//        assert AppConfig.getAndroidBG().equals("testandroid");
    }

    @Test
    public void testSubList()
    {
        List<String>s1= Arrays.asList("s1","s2","s3");
        List<String>s2=s1.subList(0,2);
        String s="";
    }
}

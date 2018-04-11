package commonUtilTests;

import commonUtil.ProjIDExtract;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by liudongdong on 2015/8/7.
 */
public class ProjIDExtractTest extends TestCase {
    public void testID()
    {
        String path1="DeleteProjInfo";
        String par1="5";
//        Assert.assertTrue(5== ProjIDExtract.extractProjID(path1,par1));


        String path2="UpdateProjInfo";
        String pa2="{\"ID\":3,\"SensorType\":5}";
//        Assert.assertTrue(3== ProjIDExtract.extractProjID(path2,pa2));

        String path3="DeleteWarnRevise";
        String pa3="{\"ProjID\":5,\"suib\":\"suibian\"}";
//        Assert.assertTrue(5== ProjIDExtract.extractProjID(path3,pa3));
    }
}

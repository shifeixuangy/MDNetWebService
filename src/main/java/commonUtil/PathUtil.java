package commonUtil;

/**
 * Created by liudongdong on 2015/11/4.
 */
public class PathUtil {
    public static String getPath(String relativePath)
    {
        relativePath=relativePath.replace('\\','/');
        return "http://"+CommonVariable.getDeployAddress()+"/"+relativePath;
    }
}

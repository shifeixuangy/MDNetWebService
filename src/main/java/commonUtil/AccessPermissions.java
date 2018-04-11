package commonUtil;

import data.common.Permission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.*;

/**
 * Created by liudongdong on 2015/4/23.
 */
public class AccessPermissions {
    private static Log logger= LogFactory.getLog(AccessPermissions.class);
    private static Set<String>prePermissions=null;
    private static Map<Integer,Set<String>>permissions=new HashMap<>();
    static
    {
//        InputStream inputStream=null;
        try (InputStream inputStream= AccessPermissions.class.getResourceAsStream(CommonVariable.ACCESS_PERMISSIONS_PATH);){
            Properties commonProperties = new Properties();
//            inputStream = AccessPermissions.class.getResourceAsStream(CommonVariable.ACCESS_PERMISSIONS_PATH);
            commonProperties.load(inputStream);
            commonProperties.stringPropertyNames().forEach(keyName->{
                Integer level=Integer.parseInt(keyName);
                Set<String>levelPermissions=new HashSet<String>();
                String []lPermissions= commonProperties.getProperty(keyName).split(",");
                levelPermissions.addAll(Arrays.asList(lPermissions));
                permissions.put(level,levelPermissions);
            });

            //在高级权限中添加低级权限的操作.
            permissions.forEach((iLevel,lsPermissions)->{
                permissions.forEach((iInnerLevel,innerPermission)->{
                    if(iLevel>iInnerLevel)
                        lsPermissions.addAll(innerPermission);
                });
            });

            String s="";
        }
        catch (Exception ex)
        {
            logger.error("访问权限初始化错误   "+ex.getMessage(),ex);
        }
    }



    public static Set<String>getLevelPermissions(Integer level)
    {
        return permissions.get(level);
    }


    public static int getLevelCount()
    {
        return permissions.size();
    }

}

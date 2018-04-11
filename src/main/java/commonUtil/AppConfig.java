package commonUtil;

import data.common.AccessType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by liudongdong on 2016/1/17.
 */
public class AppConfig {
    private static final Log logger= LogFactory.getLog(AppConfig.class);
    private static String appConfigPath;
    private static Properties appProperties;

    static {
        appConfigPath=CommonVariable.getRootPath()+"WEB-INF\\classes\\appConfig.properties";
        try (InputStream inputStream = new FileInputStream(appConfigPath)) {
            InputStreamReader reader=new InputStreamReader(inputStream,"gbk");
            appProperties = new Properties();
            appProperties.load(reader);
        } catch (Exception ex) {
            logger.warn("读取appConfig配置出错", ex);
        }
    }

    public static String getAppName(AccessType accessType) {
        return appProperties.getProperty(accessType.toString().toLowerCase()+"AppName");
    }

    public static synchronized void setAppName(String appName,AccessType accessType) {
        appProperties.setProperty(accessType.toString().toLowerCase()+"AppName",appName);
        writeToFile();
    }

    public static String getBG(AccessType accessType)
    {
        return appProperties.getProperty(accessType.toString().toLowerCase()+"BG");
    }

    public static synchronized void setBG(String bg,AccessType accessType)
    {
        appProperties.setProperty(accessType.toString().toLowerCase()+"BG",bg);
        writeToFile();
    }



    private static void writeToFile()
    {
        try(FileOutputStream os=new FileOutputStream(appConfigPath))
        {
            OutputStreamWriter writer=new OutputStreamWriter(os,"gbk");
            appProperties.store(writer,"test");
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }
}

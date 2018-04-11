package configs;

import commonUtil.CommonVariable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/4/7.
 */
public class DebugVariable {
    private static final Log logger = LogFactory.getLog(DebugVariable.class);
    private static Properties debugProperties;
    private static Boolean isUseRemoteDB;

    static {
        try (InputStream inputStream = CommonVariable.class.getResourceAsStream("/debugVariable.properties");) {
            debugProperties = new Properties();
            debugProperties.load(inputStream);
        } catch (Exception ex) {
            logger.warn("读取commonVariable配置出错", ex);
        }
    }

    public static boolean getIsUseRemoteDB() {
        if(isUseRemoteDB==null)
            isUseRemoteDB=Boolean.parseBoolean(debugProperties.getProperty("isUseRemoteDB"));
        return isUseRemoteDB;
    }
}

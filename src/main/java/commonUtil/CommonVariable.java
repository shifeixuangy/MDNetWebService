package commonUtil;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import customer.serialize.MyJsonConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.CommonProperties;

/**
 * Created by liudongdong on 2015/4/11.
 */
public class CommonVariable {
    public static final String USER_PROJS_APIS = "/userProjApis.properties";
    public static final String COMMON_VARIABLE_PATH = "/commonVariable.properties";
    public static final String ACCESS_PERMISSIONS_PATH = "/accessPermissions.properties";
    public static final String SENSOR_DATA_QUERY_PATH = "/sensorDataQuery.properties";
    public static final String SENSOR_MAX_MIN_DATA_QUERY_PATH = "/sensorMaxAndMinDataQuery.properties";
    public static final String PROJID_EXTRACT_PATH = "/projIDExtract.properties";
    /**
     * 项目数据库的Hibernate配置文件，没有设置connection.url
     */
    public static final String PROJECTS_HIBERNATE_CONFIG_PATH = "/hibernateProj.cfg.xml";
    public static final int DEFAULT_PROJID = -1;
    public static final Integer DEFAULT_AUTO_INCREMENT_ID = -1;
    public static final int DEFAULT_INT_AUTO_INCREMENT_ID = -1;
    public static final String DEFAULT_USER_PASSWORD = "111111";
    public static final String BASE_DATETIME = "2015-01-01 00:00:00";
    public static final String NOTHING_TO_RETURN = "";
    public static final String ACCESS_TOKEN_HEADER_NAME = "access_token";
    public static final String ACCESS_TYPE_HEADER_NAME="access_type";
    public static final String JSON_WITH_UTF8 = "application/json;charset=UTF-8";
    public static boolean IS_DEBUG_ENABLE = true;
    public static int CACHE_INVALIDATE_INTERVAL = 30;

    /**
     * topic 配置项
     */
    private static String TOPIC_ADDR;
    private static String TOPIC_UID;
    private static String TOPIC_PWD;
    private static String TOPIC_NAME;
    private static int JMS_RECEIVER_INIT_INTERVAL=0;
    private static Boolean IS_PUSH_ENABLE;

    private static int DEFAULT_KEY_EXPIRE_INTERVAL = 30; //单位分钟
    private static final String KEY_EXPIRE_INTERVAL_NAME = "keyExpireInterval";
    private static int DEFAULT_KEY_CHECK_INTERVAL = 300; //单位秒
    private static final String KEY_CHECK_INTERVAL_NAME = "keyCheckInterval";
    private static boolean DEFAULT_IS_AUTHENTICATE_ENABLED = false;
    private static final String IS_AUTHENTICATE_ENABLED_NAME = "isAuthenticateEnabled";
    private static String DEFAULT_API_VERSION = "1.0";
    private static final String API_VERSION_NAME = "apiVersion";
    private static String API_KEY = null;
    private static String SECRET_KEY = null;
    private static ObjectMapper objectMapper = null;
    private static String deployAddress;
    private static String projToken;
    private static Boolean isCalculateAvg;
    private static String rootPath;
    private static Boolean isPushDebugEnable;
    private static String currentDBVersion;
    private static Integer testProjID;
    private static String register;
    //是否是授权项目，通过注册码验证
    private static boolean isValid=false;
    private static String handlerWarnTopicName=null;
    private static ExecutorService executorService= Executors.newCachedThreadPool();


    private static Properties commonProperties;
    private static Log logger = LogFactory.getLog(CommonVariable.class);

    static {
        try (InputStream inputStream = CommonVariable.class.getResourceAsStream(COMMON_VARIABLE_PATH);) {
            commonProperties = new Properties();
            commonProperties.load(inputStream);
            initVariables();

        } catch (Exception ex) {
            logger.warn("读取commonVariable配置出错", ex);
        }
    }

    public static int getKeyExpireInterval() {
        return DEFAULT_KEY_EXPIRE_INTERVAL;
    }

    public static int getKeyCheckInterval() {
        return DEFAULT_KEY_CHECK_INTERVAL;
    }

    public static boolean isAuthenticateEnabled() {
        return DEFAULT_IS_AUTHENTICATE_ENABLED;
    }

    public static String getApiVersion() {
        return DEFAULT_API_VERSION;
    }

    public static String getApiKey() {
        if (StringUtil.isNullOrEmpty(API_KEY)) {
            API_KEY = commonProperties.getProperty("apiKey");
        }
        return API_KEY;
    }

    public static String getSecretKey() {
        if (StringUtil.isNullOrEmpty(SECRET_KEY)) {
            SECRET_KEY = commonProperties.getProperty("secretKey");
        }
        return SECRET_KEY;
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            MyJsonConfig jsonConfig = new MyJsonConfig();
            objectMapper = jsonConfig.getContext(null);
        }
        return objectMapper;
    }

    private static void initVariables() {
        if (commonProperties.containsKey(KEY_EXPIRE_INTERVAL_NAME)) {
            try {
                DEFAULT_KEY_EXPIRE_INTERVAL = Integer.parseInt(commonProperties.getProperty(KEY_EXPIRE_INTERVAL_NAME));
            } catch (Exception ex) {
                logger.error("访问标记过期时间配置错误", ex);
            }
        }

        try {
            if (commonProperties.containsKey(KEY_CHECK_INTERVAL_NAME)) {
                DEFAULT_KEY_CHECK_INTERVAL = Integer.parseInt(commonProperties.getProperty(KEY_CHECK_INTERVAL_NAME));
            }
        } catch (Exception ex) {
            logger.error("访问标记检查时间配置错误", ex);
        }

        if (commonProperties.containsKey(IS_AUTHENTICATE_ENABLED_NAME)) {
            try {
                DEFAULT_IS_AUTHENTICATE_ENABLED = Boolean.parseBoolean(commonProperties.getProperty(IS_AUTHENTICATE_ENABLED_NAME));
            } catch (Exception ex) {
                logger.error("是否开启验证配置错误", ex);
            }
        }

        if (commonProperties.containsKey(API_VERSION_NAME)) {
            try {
                DEFAULT_API_VERSION = commonProperties.getProperty(API_VERSION_NAME);
            } catch (Exception ex) {
                logger.error("apiversion配置文件读取失败", ex);
            }
        }
        CACHE_INVALIDATE_INTERVAL = Integer.parseInt(commonProperties.getProperty("cacheInvalidateInterval"));
        IS_DEBUG_ENABLE = Boolean.valueOf(commonProperties.getProperty("isDebugEnable"));

    }

    public static String getTOPIC_ADDR() {
        if(StringUtil.isNullOrEmpty(TOPIC_ADDR))
            TOPIC_ADDR=commonProperties.getProperty("topicAddr");
        return TOPIC_ADDR;
    }

    public static String getTOPIC_UID() {
        if(StringUtil.isNullOrEmpty(TOPIC_UID))
            TOPIC_UID=commonProperties.getProperty("topicUid");
        return TOPIC_UID;
    }

    public static String getTOPIC_PWD() {
        if(StringUtil.isNullOrEmpty(TOPIC_PWD))
            TOPIC_PWD=commonProperties.getProperty("topicPwd");
        return TOPIC_PWD;
    }

    public static String getTOPIC_NAME() {
        if(StringUtil.isNullOrEmpty(TOPIC_NAME))
            TOPIC_NAME=commonProperties.getProperty("topicName");
        return TOPIC_NAME;
    }

    public static int getJmsReceiverInitInterval()
    {
        if(JMS_RECEIVER_INIT_INTERVAL==0)
            JMS_RECEIVER_INIT_INTERVAL=Integer.parseInt(commonProperties.getProperty("jmsReceiverInitInterval"));
        return JMS_RECEIVER_INIT_INTERVAL;
    }

    public static Boolean is_PUSH_ENABLE() {
        if(IS_PUSH_ENABLE==null)
            IS_PUSH_ENABLE=Boolean.parseBoolean(commonProperties.getProperty("isPushEnable"));
        return IS_PUSH_ENABLE;
    }

    public static String getDeployAddress() {
        if(StringUtil.isNullOrEmpty(deployAddress))
            deployAddress=commonProperties.getProperty("deployAddress");
        return deployAddress;
    }

    public static String getProjToken() {
        if(StringUtil.isNullOrEmpty(projToken))
            projToken=commonProperties.getProperty("projToken");
        return projToken;
    }

    public static boolean isCalculateAvg() {
        if(isCalculateAvg==null)
        {
            isCalculateAvg=Boolean.parseBoolean(commonProperties.getProperty("isCalculateAvg"));
        }
        return isCalculateAvg;
    }

    public static boolean isPushDebugEnable() {
        if(isPushDebugEnable==null)
            isPushDebugEnable=Boolean.parseBoolean(commonProperties.getProperty("isPushDebugEnable"));
        return isPushDebugEnable;
    }

    public static String getCurrentDBVersion()
    {
        if(StringUtil.isNullOrEmpty(currentDBVersion))
            currentDBVersion=commonProperties.getProperty("currentDBVersion");
        return currentDBVersion;
    }

    public static int getTestProjID() {
        if(testProjID==null)
            testProjID=Integer.parseInt(commonProperties.getProperty("testProjID"));
        return testProjID;
    }

    public static String getRegister() {
        if(register==null)
            register=commonProperties.getProperty("register");
        return register;
    }

    public static boolean getIsValid() {
        return isValid;
    }

    public static void setIsValid(boolean isValid) {
        CommonVariable.isValid = isValid;
    }

    public static String getHandlerWarnTopicName()
    {
        if(handlerWarnTopicName==null)
            handlerWarnTopicName=commonProperties.getProperty("handlerWarnTopicName");
        return handlerWarnTopicName;
    }

    public static ExecutorService getExecutorService()
    {
        return executorService;
    }

    public static DateFormat getDefaultDateFormat()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf;
    }

    public static String getRootPath() {
        return rootPath;
    }

    public static void setRootPath(String rootPath) {
        CommonVariable.rootPath = rootPath;
    }

}

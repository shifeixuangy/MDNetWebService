package commonUtil;

import data.common.SensorType;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liudongdong on 2015/4/30.
 */
public class SensorDataSelects {
    private static final Log logger = LogFactory.getLog(SensorDataSelects.class);
    private static final Map<SensorType, String> sensorSelects = new HashMap<>();
    private static final Map<SensorType, String> sensorTables = new HashMap<>();
    private static final Map<SensorType, Class> sensorClasses = new HashMap<>();
    private static final Map<SensorType, Class> avgClasses = new HashMap<>();
    private static final Map<SensorType,Class>extremeClasses=new HashedMap();
    private static final String SELECT_PREFIX = "select_";
    private static final String TABLE_PREFIX = "table_";
    private static final String CLASS_PREFIX = "class_";
    private static final String AVG_PREFIX = "avg_";
    private static final String EXTREME_PREFIX="extreme_";

    //传感器最大最小值查询相关
    private static final Map<SensorType, String> sensorMaxDataSelects = new HashMap<>();
    private static final Map<SensorType, String> sensorMinDataSelects = new HashMap<SensorType, String>();
    private static final String SELECT_MAX_PREFIX = "select_max_";
    private static final String SELECT_MIN_PREFIX = "select_min_";


    static {
        try (InputStream inputStream = CommonVariable.class.getResourceAsStream(CommonVariable.SENSOR_DATA_QUERY_PATH);) {
            Properties commonProperties = new Properties();
            commonProperties.load(inputStream);
            commonProperties.forEach((key, value) -> {
                String sKey = key.toString();
                if (sKey.startsWith(SELECT_PREFIX)) {
                    SensorType sType = SensorType.valueOf(sKey.replace(SELECT_PREFIX, ""));
                    sensorSelects.put(sType, value.toString());
                }
                if (sKey.startsWith(TABLE_PREFIX)) {
                    SensorType sType = SensorType.valueOf(sKey.replace(TABLE_PREFIX, ""));
                    sensorTables.put(sType, value.toString());
                }
                if (sKey.startsWith(CLASS_PREFIX)) {
                    SensorType sType = SensorType.valueOf(sKey.replace(CLASS_PREFIX, ""));
                    try {
                        sensorClasses.put(sType, Class.forName(value.toString()));
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }
                if (sKey.startsWith(AVG_PREFIX)) {
                    SensorType sType = SensorType.valueOf(sKey.replace(AVG_PREFIX, ""));
                    try {
                        avgClasses.put(sType, Class.forName(value.toString()));
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }
                if (sKey.startsWith(EXTREME_PREFIX)) {
                    SensorType sType = SensorType.valueOf(sKey.replace(EXTREME_PREFIX, ""));
                    try {
                        extremeClasses.put(sType, Class.forName(value.toString()));
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }
            });
            initSensorMaxAndMinDataQuery();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }


    public static String getSensorSelect(SensorType sType) {
        return sensorSelects.get(sType);
    }

    public static String getSensorTable(SensorType sType) {
        return sensorTables.get(sType);
    }

    public static Class getSensorClass(SensorType sType) {
        return sensorClasses.get(sType);
    }



    public static Class getSensorClass(SensorType sType, boolean isAvg) {
        if (isAvg)
            return avgClasses.get(sType);
        else
            return sensorClasses.get(sType);
    }

    public static Class getExtremeSensorClass(SensorType sType)
    {
        if(extremeClasses.containsKey(sType))
            return extremeClasses.get(sType);
        return sensorClasses.get(sType);
    }


    public static String getSensorMaxAndMinSelect(SensorType sType, boolean isMax) {
        if (isMax)
            return sensorMaxDataSelects.get(sType);
        else
            return sensorMinDataSelects.get(sType);
    }

    private static void initSensorMaxAndMinDataQuery() {
        try (InputStream inputStream = CommonVariable.class.getResourceAsStream(CommonVariable.SENSOR_MAX_MIN_DATA_QUERY_PATH);) {
            Properties commonProperties = new Properties();
            commonProperties.load(inputStream);
            commonProperties.forEach((key, value) -> {
                String sKey = key.toString();
                if (sKey.startsWith(SELECT_MAX_PREFIX)) {
                    SensorType sType = SensorType.valueOf(sKey.replace(SELECT_MAX_PREFIX, ""));
                    sensorMaxDataSelects.put(sType, value.toString());
                }
                if (sKey.startsWith(SELECT_MIN_PREFIX)) {
                    SensorType sType = SensorType.valueOf(sKey.replace(SELECT_MIN_PREFIX, ""));
                    sensorMinDataSelects.put(sType, value.toString());
                }
            });
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}

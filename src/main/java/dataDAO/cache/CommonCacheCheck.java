package dataDAO.cache;

import data.common.*;
import data.proj.*;

/**
 * Created by liudongdong on 2015/6/8.
 */
public class CommonCacheCheck {
    private static CommonCache commonCache = CommonCache.getCommonCache();

    public static void checkObject(Object o, int projID) {
        if (o == null)
            return;
        if (o instanceof TbProjInfo)
            commonCache.invalidate(CommonCache.PROJ_INFO);
        if (o instanceof TbProjManager)
            commonCache.invalidate(CommonCache.PROJ_MANAGER);
        if (o instanceof TbProjPower)
            commonCache.invalidate(CommonCache.PROJ_POWER);
        if (o instanceof TbUser)
            commonCache.invalidate(CommonCache.USER);
        if (o instanceof TbRegion)
            commonCache.invalidate(CommonCache.REGION);
        if (o instanceof TbRegionManager)
            commonCache.invalidate(CommonCache.REGION_MANAGER);
        if (o instanceof TbCompany)
            commonCache.invalidate(CommonCache.COMPANY);
        if (o instanceof TbLevel)
            commonCache.invalidate(CommonCache.LEVEL);
        if (o instanceof TbProjType)
            commonCache.invalidate(CommonCache.PROJ_TYPE);

        if (o instanceof TbSensorAttri) {
            invalidateProjectCache(projID, ProjCache.SENSOR_CACHE);
        }
        if (o instanceof TbMonitorType) {
            invalidateProjectCache(projID, ProjCache.MONITOR_TYPE_CACHE);
        }
        if (o instanceof TbChannel) {
            invalidateProjectCache(projID, ProjCache.CHANNEL_CACHE);
        }
        if (o instanceof TbImageMeta) {
            invalidateProjectCache(projID, ProjCache.IMAGE_META_CACHE);
        }
        if (o instanceof TbGroup) {
            invalidateProjectCache(projID, ProjCache.GROUP_CACHE);
        }
    }

    public static void checkDelete(Object o, int projID) {
        if (o == null)
            return;
        if (o instanceof TbCompany)
            commonCache.invalidate(CommonCache.USER);
        if (o instanceof TbLevel)
            commonCache.invalidate(CommonCache.PROJ_INFO);
    }

    private static void invalidateProjectCache(int projID, String key) {
        ProjCache piCache = ProjCache.getProjCache(projID);
        if (piCache != null)
            piCache.invalidate(key);
    }
}

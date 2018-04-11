package dataDAO.cache;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.SensorType;
import data.common.TbProjInfo;
import data.proj.*;
import dataDAO.impl.ProjectInfoQuery;
import dataDAO.interfaces.ObjectsQuery;
import dataDAO.proj.impl.DBProjQueryHelper;
import dataDAO.proj.interfaces.IProjQueryHelper;
import jersey.repackaged.com.google.common.cache.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/6/8.
 */
public class ProjCache {
    public static final String SENSOR_CACHE = "sensorAttri";
    public static final String MONITOR_TYPE_CACHE = "monitorType";
    public static final String CHANNEL_CACHE = "channel";
    public static final String IMAGE_META_CACHE = "imageMeta";
    public static final String GROUP_CACHE = "groupCache";
    private static Log logger = LogFactory.getLog(ProjCache.class);
    private static final String LOAD_INFO = "加载缓存：  ";
    private static Map<Integer, ProjCache> projsCaches;
    private static Object syncRoot = new Object();


    private IProjQueryHelper projQueryHelper = new DBProjQueryHelper();
    private int projID;
    //String:sensor cache的key       对应该key的sensores
    private LoadingCache<String, List<TbSensorAttri>> sensorCache;
    private LoadingCache<String, List<TbMonitorType>> monitorTypeCache;
    private LoadingCache<String, List<TbChannel>> channelCache;
    private LoadingCache<String, List<TbImageMeta>> imageMetaCache;
    private LoadingCache<String, List<TbGroup>> groupCache;
    private List<LoadingCache> caches = new LinkedList<>();

    private ProjCache(int projID) {
        this.projID = projID;
        initSensorAttriCache();
        initMonitorTypeCache();
        initChannelCache();
        initImageMetaCache();
        initGroupCache();
        caches.addAll(Arrays.asList(sensorCache, monitorTypeCache, channelCache, imageMetaCache, groupCache));
    }

    static {
        if (projsCaches == null) {
            synchronized (syncRoot) {
                if (projsCaches == null) {
                    projsCaches = new HashMap<>();
                    try {
                        ObjectsQuery<TbProjInfo> piQuery = new ProjectInfoQuery();
                        piQuery.query(null, CommonVariable.DEFAULT_PROJID).forEach(pi -> {
                            projsCaches.put(pi.getId(), new ProjCache(pi.getId()));
                        });
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                        throw ex;
                    }
                }
            }
        }
    }

    public static ProjCache getProjCache(int projID) {
        return projsCaches.get(projID);
    }

    public static void addProjCache(int pid) {
        synchronized (syncRoot) {
            projsCaches.put(pid, new ProjCache(pid));
        }
    }

    public static void removeProjCache(int pid) {
        synchronized (syncRoot) {
            projsCaches.remove(pid);
        }
    }


    public List<TbSensorAttri> getSensors() {
        return sensorCache.getUnchecked(SENSOR_CACHE);
    }

    public List<TbMonitorType> getMonitorTypes() {
        return monitorTypeCache.getUnchecked(MONITOR_TYPE_CACHE);
    }

    public List<TbChannel> getChannels() {
        return channelCache.getUnchecked(CHANNEL_CACHE);
    }

    public List<TbImageMeta> getImages(boolean isProjImage) {
        List<TbImageMeta> images = imageMetaCache.getUnchecked(IMAGE_META_CACHE);
        if (CollectionUtil.isNullOrEmpty(images))
            return Collections.emptyList();
        return images.stream().filter(im -> im.getIsProjImage() == isProjImage).collect(Collectors.toList());
    }

    public List<TbGroup> getGroups() {
        return groupCache.getUnchecked(GROUP_CACHE);
    }

    public void invalidate(String key) {
        caches.forEach(lc -> {
            if (lc.asMap().containsKey(key))
                lc.invalidate(key);
        });
    }

    private void initSensorAttriCache() {
        sensorCache = cached(new CacheLoader<String, List<TbSensorAttri>>() {
            @Override
            public List<TbSensorAttri> load(String key) throws Exception {
                try {
                    logger.info(LOAD_INFO + key);
                    return projQueryHelper.getSensorList(ProjCache.this.projID, SensorType.All.toInt());
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                    throw ex;
                }
            }
        });
    }

    private void initMonitorTypeCache() {
        monitorTypeCache = cached(new CacheLoader<String, List<TbMonitorType>>() {
            @Override
            public List<TbMonitorType> load(String key) throws Exception {
                try {
                    logger.info(LOAD_INFO + key);
                    return projQueryHelper.getMonitorType(ProjCache.this.projID);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                    throw ex;
                }
            }
        });
    }

    private void initChannelCache() {
        channelCache = cached(new CacheLoader<String, List<TbChannel>>() {
            @Override
            public List<TbChannel> load(String key) throws Exception {
                try {
                    logger.info(LOAD_INFO + key);
                    return projQueryHelper.getChannels(ProjCache.this.projID);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                    throw ex;
                }
            }
        });
    }

    private void initImageMetaCache() {
        imageMetaCache = cached(new CacheLoader<String, List<TbImageMeta>>() {
            @Override
            public List<TbImageMeta> load(String key) throws Exception {
                try {
                    logger.info(LOAD_INFO + key);
                    return projQueryHelper.getImages(ProjCache.this.projID);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                    throw ex;
                }
            }
        });
    }

    private void initGroupCache() {
        groupCache = cached(new CacheLoader<String, List<TbGroup>>() {
            @Override
            public List<TbGroup> load(String key) throws Exception {
                try {
                    logger.info(LOAD_INFO + key);
                    return projQueryHelper.getGroup(ProjCache.this.projID, CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                    throw ex;
                }
            }
        });
    }

    private <K, V> LoadingCache<K, V> cached(CacheLoader<K, V> cacheLoader) {
        LoadingCache<K, V> cache = CacheBuilder
                .newBuilder()
                .refreshAfterWrite(CommonVariable.CACHE_INVALIDATE_INTERVAL, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<K, V>() {
                    @Override
                    public void onRemoval(RemovalNotification<K, V> rn) {
                        logger.info(rn.getKey() + "被移除");

                    }
                })
                .build(cacheLoader);
        return cache;
    }


    public int getProjID() {
        return projID;
    }
}

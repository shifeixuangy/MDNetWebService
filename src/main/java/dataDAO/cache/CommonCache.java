package dataDAO.cache;

import commonUtil.CommonVariable;
import data.common.*;
import dataDAO.impl.DBCommonQueryHelper;
import dataDAO.interfaces.ICommonQueryHelper;
import jersey.repackaged.com.google.common.cache.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by liudongdong on 2015/6/8.
 */
public class CommonCache {
    private static CommonCache cache;
    private static Object syncRoot=new Object();
    private static final Log logger= LogFactory.getLog(CommonCache.class);
    private static final String LOAD_INFO="加载缓存：  ";
    public static final String PROJ_INFO="projInfo";
    public static final String USER="user";
    public static final String PROJ_POWER="projPower";
    public static final String PROJ_MANAGER="projManager";
    public static final String REGION="region";
    public static final String REGION_MANAGER="regionManager";
    public static final String COMPANY="company";
    public static final String LEVEL="level";
    public static final String PROJ_TYPE="projType";
    private ICommonQueryHelper commonQueryHelper=new DBCommonQueryHelper();
    private LoadingCache<String,List<TbProjInfo>>projInfoCache;
    private LoadingCache<String,List<TbUser>>userCache;
    private LoadingCache<String,List<TbProjPower>>projPowerCache;
    private LoadingCache<String,List<TbProjManager>>projManagerCache;
    private LoadingCache<String,List<TbRegion>>regionCache;
    private LoadingCache<String,List<TbRegionManager>>regionManagerCache;
    private LoadingCache<String,List<TbCompany>>companyCache;
    private LoadingCache<String,List<TbLevel>>levelCache;
    private LoadingCache<String,List<TbProjType>>projTypeCache;
    private List<LoadingCache>caches=new LinkedList<>();
//    private LoadingCache<String,List<TbLog>>logCache;

    private CommonCache()
    {
        initProjInfoCache();
        initProjManagerCache();
        initUserCache();
        initProjPowerCache();
        initRegionCache();
        initRegionManagerCache();
        initCompanyCache();
        initLevelCache();
        initProjTypeCache();
        caches.addAll(Arrays.asList(projInfoCache,userCache,projPowerCache
                ,projManagerCache,regionCache,regionManagerCache,companyCache,levelCache,projTypeCache));
    }

    public static CommonCache getCommonCache()
    {
        if(cache==null)
        {
            synchronized (syncRoot)
            {
                if(cache==null)
                    cache=new CommonCache();
            }
        }
        return cache;

    }
    public List<TbProjInfo>getProjInfos()
    {
        try {
            return projInfoCache.get(PROJ_INFO);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            throw new RuntimeException(ex);
        }
    }
    public List<TbUser>getUsers()
    {
        try {
            return userCache.get(USER);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            throw  new RuntimeException(ex);
        }
    }
    public List<TbProjPower>getProjPowers()
    {
        return projPowerCache.getUnchecked(PROJ_POWER);
    }
    public List<TbProjManager>getProjManagers()
    {
        return projManagerCache.getUnchecked(PROJ_MANAGER);
    }
    public List<TbRegion>getRegions()
    {
        return regionCache.getUnchecked(REGION);
    }
    public List<TbRegionManager>getRegionManagers()
    {
        return regionManagerCache.getUnchecked(REGION_MANAGER);
    }
    public List<TbCompany>getCompanies(){
        return companyCache.getUnchecked(COMPANY);
    }
    public List<TbLevel>getLevels(){return levelCache.getUnchecked(LEVEL);}
    public List<TbProjType>getProjTypes(){ return  projTypeCache.getUnchecked(PROJ_TYPE); }

    public void invalidate(String key)
    {
        caches.forEach(lc->{
            if(lc.asMap().containsKey(key))
                lc.invalidate(key);
        });
    }

    private void initProjInfoCache()
    {
        projInfoCache=cached(new CacheLoader<String, List<TbProjInfo>>() {
            @Override
            public List<TbProjInfo> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getProjInfo();
                }
                catch (Exception ex)
                {
                    logger.error("加载项目信息出错",ex);
                    throw  ex;
                }
            }
        });
    }

    private void initUserCache()
    {
        userCache=cached(new CacheLoader<String, List<TbUser>>() {
            @Override
            public List<TbUser> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getUser();
                }
                catch (Exception ex)
                {
                    logger.error("加载用户缓存出错",ex);
                    throw  ex;
                }
            }
        });
    }
    private void initProjPowerCache()
    {
        projPowerCache=cached(new CacheLoader<String, List<TbProjPower>>() {
            @Override
            public List<TbProjPower> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getProjPower(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
                }
                catch (Exception ex)
                {
                    logger.error("加载项目权限出错",ex);
                    throw  ex;
                }
            }
        });
    }
    private void initProjManagerCache()
    {
        projManagerCache=cached(new CacheLoader<String, List<TbProjManager>>() {
            @Override
            public List<TbProjManager> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getProjManager();
                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                    throw  ex;
                }
            }
        });
    }
    private void initRegionCache()
    {
        regionCache=cached(new CacheLoader<String, List<TbRegion>>() {
            @Override
            public List<TbRegion> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return  commonQueryHelper.getRegion(null);
                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                    throw  ex;
                }
            }
        });
    }
    private void initRegionManagerCache()
    {
        regionManagerCache=cached(new CacheLoader<String, List<TbRegionManager>>() {
            @Override
            public List<TbRegionManager> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getRegionManager();
                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                    throw  ex;
                }
            }
        });
    }
    private void initCompanyCache()
    {
        companyCache=cached(new CacheLoader<String, List<TbCompany>>() {
            @Override
            public List<TbCompany> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getCompanyList(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                    throw  ex;
                }
            }
        });
    }

    private void initLevelCache()
    {
        levelCache=cached(new CacheLoader<String, List<TbLevel>>() {
            @Override
            public List<TbLevel> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getLevelList();
                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                    throw  ex;
                }
            }
        });
    }

    private void initProjTypeCache()
    {
        projTypeCache=cached(new CacheLoader<String, List<TbProjType>>() {
            @Override
            public List<TbProjType> load(String key) throws Exception {
                try
                {
                    logger.info(LOAD_INFO+key);
                    return commonQueryHelper.getProjTypeList();
                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                    throw  ex;
                }
            }
        });
    }

    private   <K,V>LoadingCache<K,V> cached(CacheLoader<K , V> cacheLoader) {
        LoadingCache<K , V> cache = CacheBuilder
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

}

package dataDAO;

import commonUtil.CommonVariable;
import dataDAO.cache.CommonCache;
import dataDAO.cache.CommonCacheCheck;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/26.
 */
public class DataHelper {
    private static ObjectOperation operation=new ObjectOperationImpl();
    public static Integer add(Object o,int projID)
    {
        Integer i= operation.addObject(o,projID);
        CommonCacheCheck.checkObject(o,projID);
        return i;
    }

    public static void addWithoutKey(Object o,int projID)
    {
        operation.addObjectWithoutKey(o,projID);
        CommonCacheCheck.checkObject(o,projID);
    }

    public static void addBatch(List<? extends Object>datas,int projID)
    {
        if((datas==null)||(datas.size()<=0))
            return;
        operation.addBatch(datas,projID);
        CommonCacheCheck.checkObject(datas.get(0),projID);
    }

    public static void update(Object o,int projID)
    {
        operation.updateObject(o,projID);
        CommonCacheCheck.checkObject(o,projID);
    }

    public static void saveOrUpdate(Object o,int projID)
    {
        operation.saveOrUpdateObject(o,projID);
        CommonCacheCheck.checkObject(o,projID);
    }

    public static void delete(Object o,int projID)
    {
        operation.deleteObject(o,projID);
        CommonCacheCheck.checkObject(o,projID);
        CommonCacheCheck.checkDelete(o,projID);
    }

    public static void deleteDatabase(String dbName)
    {
        String sql="drop schema "+dbName;
        ExecuteSql.execute(CommonVariable.DEFAULT_PROJID,sql);
    }
    public static void deleteProjMgr(int uid,int projID)
    {
        String delete="delete from tb_proj_manager where UID="+Integer.toString(uid)+"   and ProjID="+Integer.toString(projID);
        ExecuteSql.execute(CommonVariable.DEFAULT_PROJID,delete);
        CommonCache.getCommonCache().invalidate(CommonCache.PROJ_MANAGER);
    }
    public static void deleteRegionMgr(int uid,int regionID)
    {
        String sql="delete from tb_region_manager where UID="+Integer.toString(uid)
                +"   and RegionID="+Integer.toString(regionID);
        ExecuteSql.execute(CommonVariable.DEFAULT_PROJID,sql);
        CommonCache.getCommonCache().invalidate(CommonCache.REGION_MANAGER);
    }

}

package dataDAO;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/11.
 */
public interface ObjectOperation {
    public Integer addObject(Object o,int projID);
    public void addObjectWithoutKey(Object o,int projID);
    public void addBatch(List<? extends Object>datas,int projID);
    public void updateObject(Object o ,int projID);
    public void saveOrUpdateObject(Object o,int projID);
    public  void deleteObject(Object o,int projID);
}

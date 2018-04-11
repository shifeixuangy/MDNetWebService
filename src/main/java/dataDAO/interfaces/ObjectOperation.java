package dataDAO.interfaces;

/**
 * Created by liudongdong on 2015/4/19.
 */
public interface ObjectOperation {
    public void addObject(Object o,int projID);
    public void updateObject(Object o ,int projID);
    public  void deleteObject(Object o,int projID);
}

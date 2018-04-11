package dataDAO.proj.interfaces;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/30.
 */
public interface NativeSqlQuery<T> {
    List<T>query(int projID,String sql,Class<T>tClass);
}

package dataDAO.interfaces;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public interface CommonQuery<T> {
    List<T>query(String from,String where,int projID);
}

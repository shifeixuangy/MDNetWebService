package dataDAO.interfaces;
import data.common.TbProjInfo;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/19.
 */
public interface ObjectsQuery<T> {
    List<T> query(String where,int projID);
}

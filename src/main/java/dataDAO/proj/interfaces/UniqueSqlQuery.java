package dataDAO.proj.interfaces;

import org.hibernate.type.Type;
import java.util.Map;

/**
 * Created by liudongdong on 2015/5/7.
 */
public interface UniqueSqlQuery<T> {
    public T query(int projID,String sql,Map<String,Type>columnTypes);
}

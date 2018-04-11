package commonUtil;

import java.util.List;

/**
 * Created by liudongdong on 2015/10/7.
 */
public class CollectionUtil {
    public  static boolean isNullOrEmpty(List data)
    {
        return data==null||data.size()==0;
    }
}

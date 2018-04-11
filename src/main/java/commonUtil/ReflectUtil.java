package commonUtil;

import java.lang.reflect.Field;

/**
 * Created by liudongdong on 2016/2/18.
 */
public class ReflectUtil {
    public static Object getDataObject(int id,Class c)
    {
        try {
            Object obj = c.newInstance();
            Field idField= c.getDeclaredField("id");
            idField.setAccessible(true);
            idField.setInt(obj,id);
            return obj;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}

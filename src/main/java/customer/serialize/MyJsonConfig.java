package customer.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Created by liudongdong on 2015/5/22.
 */
@Provider
public class MyJsonConfig implements ContextResolver<ObjectMapper> {
    private ObjectMapper om=new ObjectMapper();

    public MyJsonConfig()
    {
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //要注意格式字幕是区分大小写的
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Shanghai")));
        om.setDateFormat(sdf);
        om.getDeserializationConfig().with(sdf);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return om;
    }
}

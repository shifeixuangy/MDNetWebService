package commonUtil;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liudongdong on 2015/4/23.
 */
public class AccessTokenGenerate {

    private static  final AccessTokenGenerate tGenerate=new AccessTokenGenerate();
    public static String getAccessToken()
    {
        return tGenerate.getToken();
    }



    private AtomicInteger autoInt=new AtomicInteger(0);

    private AccessTokenGenerate()
    {}

    public String getToken()
    {
        String uuidPart= UUID.randomUUID().toString();
        String intPart=Integer.toString( autoInt.incrementAndGet());
        return uuidPart+intPart;
    }

}

package commonUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/15.
 */
public class TimeUtil {
    public static String format(Timestamp time)
    {
        DateFormat df=CommonVariable.getDefaultDateFormat();
        return df.format(time);
    }

    public static double minusDay(Timestamp pre,Timestamp next)
    {
        return (next.getTime()-pre.getTime())/(24*3600*1000);
    }

    public static List<Timestamp>getTimePreDayBeginEnd(Timestamp time)
    {
        LocalDateTime dt=time.toLocalDateTime();
        dt=dt.minusDays(1);
        String str= dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String minStr=str+" 00:00:00";
        String maxStr=str+" 23:59:59";
        List<Timestamp>result=new LinkedList<>();
        result.add(Timestamp.valueOf(minStr));
        result.add(Timestamp.valueOf(maxStr));
        return result;
    }

    /**
     * 根据时间戳获取时间
     * @param str 一个整型数
     * @return
     */
    public static LocalDateTime valueOf(String str)
    {
        return new Timestamp(Long.parseLong(str)).toLocalDateTime();
    }
}

package init.config;

import commonUtil.CommonVariable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liudongdong on 2015/12/9.
 */
public class LogCleanerInit {
    private static Log logger= LogFactory.getLog(LogCleanerInit.class);
    private Pattern filePattern=Pattern.compile("\\d+-\\d+-\\d+");
    private  static LogCleanerInit lci=new LogCleanerInit();
    public  static void init()
    {
        ScheduledExecutorService ses= Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(()->{
            try
            {
                lci.clear();
            }
            catch (Exception ex)
            {
                logger.error(ex.getMessage(),ex);
            }
        },0,24*60*60*1000, TimeUnit.MILLISECONDS);
    }

    public void clear()
    {
        LocalDateTime now=LocalDateTime.now();
        LocalDateTime begin=now.plusMonths(-1);
        String path=CommonVariable.getRootPath()+"WEB-INF\\logs";
        File root=new File(path);
        String[]fileNames= root.list();
        for(String s:fileNames)
        {
            Matcher m=filePattern.matcher(s);
            if(!m.find())
                continue;
            String dateString= m.group();
            LocalDateTime fileTime=LocalDateTime.parse(dateString+" 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            if(fileTime.isBefore(begin))
            {
                File f=new File(path+File.separator+s);
                f.delete();
            }
        }
    }
}

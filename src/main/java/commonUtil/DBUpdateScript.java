package commonUtil;

import data.common.DBUpdateInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by liudongdong on 2016/2/25.
 */
public class DBUpdateScript {
    private static Log logger= LogFactory.getLog(DBUpdateScript.class);
    private static List<DBUpdateInfo>dbInfos;
    static
    {
        try {
            String path = CommonVariable.getRootPath() + "WEB-INF\\classes\\dbupdate.txt";
            InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8);
            BufferedReader bufferedReader=new BufferedReader(reader);
            StringBuilder stringBuilder=new StringBuilder();
            bufferedReader.lines().filter(s->!s.startsWith("##")).forEach(stringBuilder::append);
            if(stringBuilder.length()>0)
            {
                DBUpdateInfo[] arrScript=CommonVariable.getObjectMapper().readValue(stringBuilder.toString(),DBUpdateInfo[].class);
                dbInfos= Arrays.asList(arrScript);
            }
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }


    public static DBUpdateInfo getDBUpdateInfo(String dbFromVersion)
    {
        if(CollectionUtil.isNullOrEmpty(dbInfos))
            return null;
        Optional<DBUpdateInfo>dbUpdateInfoOptional=dbInfos.stream().filter(db->db.getFrom().equals(dbFromVersion)).findFirst();
        return dbUpdateInfoOptional.isPresent()?dbUpdateInfoOptional.get():null;
    }


}

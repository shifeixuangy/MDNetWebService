package init.config;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.DBUpdateScript;
import commonUtil.DBUtil;
import data.common.DBUpdateInfo;
import data.common.TbProjInfo;
import dataDAO.CommonQueryHelper;
import dataDAO.ExecuteSql;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2016/2/25.
 */
public class DBUpdateService {
    private static Log logger= LogFactory.getLog(DBUpdateService.class);

    public static void start()
    {
        List<TbProjInfo>pis= CommonQueryHelper.getProjInfo();
        if(CollectionUtil.isNullOrEmpty(pis))
            return;
        final DBUpdateService service=new DBUpdateService();
        pis.forEach(pi->{
            try
            {
                if(service.isNeedUpdate(pi.getId()))
                    service.update(pi.getId());
            }
            catch (Exception ex)
            {
                logger.error(ex.getMessage(),ex);
            }
        });
    }

    private boolean isNeedUpdate(int projID)
    {
        if(!DBUtil.isTableExists(projID,"db_info"))
            return false;
        String dbVersion=DBUtil.getDBVersion(projID);
        if(CommonVariable.getCurrentDBVersion().equals(dbVersion))
            return false;
        return true;
    }

    private void update(int projID)
    {
        if(!isNeedUpdate(projID))
            return;
        String dbVersion=DBUtil.getDBVersion(projID);
        String currentDBVersion=CommonVariable.getCurrentDBVersion();
        while (!dbVersion.equals(currentDBVersion))
        {
            DBUpdateInfo dbUpdateInfo= DBUpdateScript.getDBUpdateInfo(dbVersion);
            if(dbUpdateInfo==null)
            {
                logger.error("数据库需要升级，但是没有获取到升级脚本信息。数据库中的版本信息："+dbVersion+"最新版本："+currentDBVersion);
                break;
            }
            String[] sqls=dbUpdateInfo.getScript().split(";");
            List<String>sqlList= Arrays.asList(sqls).stream().map(s->s+";").collect(Collectors.toList());
            ExecuteSql.executeBatch(projID,sqlList);
            String preDBVersion=dbVersion;
            dbVersion=dbUpdateInfo.getTo();
            DBUtil.setDBVersion(projID,preDBVersion,dbVersion);
            logger.info("项目："+Integer.toString(projID)+"  数据库升级："+preDBVersion+"-"+dbVersion);
        }
    }
}

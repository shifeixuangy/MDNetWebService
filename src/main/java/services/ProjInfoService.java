package services;

import commonUtil.*;
import data.common.*;
import data.proj.WarnLogStatus;
import data.response.results.GetProjListResult;
import data.response.results.GetProjWarnNumberListResult;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;
import dataDAO.cache.ProjCache;
import init.config.TagInit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/5/5.
 */
public class ProjInfoService {
    private static final Log logger= LogFactory.getLog(ProjInfoService.class);
    public List<GetProjListResult>getProjList(String accessToken,int projID)
    {
        final List<GetProjListResult>results=new LinkedList<>();
        UserInformation userInfo= AccessTokenPool.getInstance().getUserInformation(accessToken);
        if(userInfo==null)
            return null;
        List<TbProjInfo>pis= new LinkedList<>();
        if(userInfo.isAdmin())
        {
            pis.addAll(CommonQueryHelper.getProjInfo());
        }
        else
        {
            List<TbProjInfo> projInfos=CommonQueryHelper.getProjInfo(userInfo.getUserProjIDs());
            if(projInfos!=null)
            {
                for(TbProjInfo pi:projInfos)
                {
                    TbProjInfo piNew=pi.clone();
                    piNew.setConnectString(null);
                    pis.add(piNew);
                }
//                projInfos.forEach(pi->{
//                    TbProjInfo piNew=pi.clone();
//                    piNew.setConnectString(null);
//                    pis.add(piNew);
//                });
            }
        }
        if(projID!=CommonVariable.DEFAULT_PROJID)
            pis=pis.stream().filter(pi->pi.getId()==projID).collect(Collectors.toList());
        pis.forEach(pi->{
            GetProjListResult piResult=new GetProjListResult(pi);
            Timestamp begin=Timestamp.valueOf(LocalDateTime.now().minusWeeks(1));
            Timestamp end=Timestamp.valueOf(LocalDateTime.now());
            int warnNum= ProjQueryHelper.getWranLogCount(pi.getId(),CommonVariable.DEFAULT_AUTO_INCREMENT_ID,
                    begin,end, WarnLogStatus.UNDEALED,-1);
            piResult.setLastWeekWarnNum(warnNum);
            results.add(piResult);
        });
        return results;
    }

    public List<GetProjWarnNumberListResult>getProjWarnNumberList(String accessToken)
    {
        List<GetProjListResult>pis=getProjList(accessToken,CommonVariable.DEFAULT_PROJID);
        if(CollectionUtil.isNullOrEmpty(pis))
            return Collections.emptyList();
        List<GetProjWarnNumberListResult>result=new LinkedList<>();
        pis.forEach(pi->{
            GetProjWarnNumberListResult r=new GetProjWarnNumberListResult();
            r.setProjID(pi.getProjID());
            r.setProjName(pi.getName());
            r.setLastWeekWarnNumber(pi.getLastWeekWarnNum());
            Timestamp begin=Timestamp.valueOf(LocalDateTime.now().minusWeeks(1));
            Timestamp end=Timestamp.valueOf(LocalDateTime.now());
            int nonDataWarnNum= ProjQueryHelper.getWranLogCount(pi.getProjID(),CommonVariable.DEFAULT_AUTO_INCREMENT_ID,
                    begin,end, WarnLogStatus.UNDEALED, WarnType.DEVICE_WARN);
            r.setLastWeekNonDataWarnNumber(nonDataWarnNum);
            result.add(r);
        });
        return result;
    }

    public Integer addProjInfo(TbProjInfo pi)
    {
        Integer pid= DataHelper.add(pi,CommonVariable.DEFAULT_PROJID);

        DBConfig commonConfig=DBConfig.getCommonConfig();
        DBConfig projConfig=new DBConfig(commonConfig.getAddr(),"mdm_"+pid.toString()+"_db",commonConfig.getUid(),commonConfig.getPwd());
        pi.setConnectString(projConfig.getDBStorageString());
        //创建数据库?
        //初始化SessionFactory,如果该数据库不存在，则创建一个
        try {
            DBUtil.initProjSessionFactory(pi);
            DataHelper.update(pi,CommonVariable.DEFAULT_PROJID);
            //初始化缓存
            ProjCache.addProjCache(pi.getId());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            DataHelper.delete(pi,CommonVariable.DEFAULT_PROJID);
        }
        //初始化百度push分组信息
        try {
            if(CommonVariable.is_PUSH_ENABLE()) {
                TagInit tagInit = TagInit.getDefaultTagInit();
                tagInit.addTag(TagInit.getProjTagName(pi.getId()));
            }
        }
        catch (Exception ex)
        {
            logger.error( ex.getMessage(),ex);
        }
        return pid;
    }


    public void updateProjInfo( TbProjInfo pi)
    {
        TbProjInfo piMemory=CommonQueryHelper.getProjInfo(pi.getId());
        if(piMemory!=null) {
            pi.setConnectString(piMemory.getConnectString());
            DataHelper.update(pi, CommonVariable.DEFAULT_PROJID);
        }
    }

    public void deleteProjInfo(int projID)
    {
        TbProjInfo pi=CommonQueryHelper.getProjInfo(projID);
        if(pi==null)
            return;
        DataHelper.delete(pi,CommonVariable.DEFAULT_PROJID);
        ConnectionStringExtract cse=new ConnectionStringExtract(pi.getConnectString());
        DataHelper.deleteDatabase(cse.getDbName());
        ProjCache.removeProjCache(projID);
        try
        {
            if(CommonVariable.is_PUSH_ENABLE()) {
                TagInit tagInit = TagInit.getDefaultTagInit();
                tagInit.deleteTag(TagInit.getProjTagName(projID));
            }
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }

}

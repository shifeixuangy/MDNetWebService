package services;

import commonUtil.AccessTokenPool;
import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.UserInformation;
import data.proj.TbWarnLog;
import data.request.parameters.*;
import data.response.results.GetWarnLogListResultWarpper;
import data.response.results.ProjWarnCountAndLevelResult;
import data.response.results.WarnLogCountResult;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.ExecuteSql;
import dataDAO.ProjQueryHelper;
import dataDAO.proj.impl.NativeSqlQueryImpl;
import dataDAO.proj.interfaces.NativeSqlQuery;
import init.config.TopicSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/7.
 */
public class WarnLogService {
    private static Log logger= LogFactory.getLog(WarnLogService.class);

    public List<WarnLogCountResult> getWarnLogCountList(GetWarnLogCountListParameter pa) {
        return ProjQueryHelper.getWarnLogCountList(pa.getProjID(), pa.getStartTime(), pa.getEndTime());
    }

    public GetWarnLogListResultWarpper getWarnLogList(GetWarnLogListParameter pa) {
        return ProjQueryHelper.getWarnLogList(pa);
    }


    public boolean hasWarnLog(String accessToken,Timestamp begin,Timestamp end)
    {
        UserInformation userInfo= AccessTokenPool.getInstance().getUserInformation(accessToken);
        if(userInfo==null)
            return false;
        List<Integer>projIDs=userInfo.getUserProjIDs();
        if(projIDs==null||projIDs.size()==0)
            return false;
        return ProjQueryHelper.hasWarnLog(projIDs,begin,end);
    }


    public void dealWarnLog(DealWarnLogParameter pa, int uid) {
        TbWarnLog log = ProjQueryHelper.getWarnLog(pa.getProjID(), pa.getID());
        if (log == null)
            return;
        log.setDealed(true);
        log.setDealName(pa.getDealName());
        log.setDealPhone(pa.getDealPhone());
        DataHelper.update(log, pa.getProjID());
        sendWarnHandlerMsg(pa.getProjID());
    }

    public void batchDealWarnLog(BatchDealWarnLogParameter pa, int uid) {
        String sql = "update tb_warn_log set Dealed=1,DealName='" + pa.getDealName()
                + "',DealPhone='" + pa.getDealPhone() + "' where SensorID=" + Integer.toString(pa.getSensorID()) + ";";
        ExecuteSql.execute(pa.getProjID(), sql);
        sendWarnHandlerMsg(pa.getProjID());
    }

    public ProjWarnCountAndLevelResult getProjWarnCountAndLevel(GetProjWarnCountAndLevelParameter pa)
    {
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append(" select 0 as ProjID, count(ID) as UnHandlerCount,max(WarnLevel) as WarnLevel ");
        sqlBuilder.append(" from tb_warn_log where `Time` between '"+pa.getBegin().toString()
                +"' and '"+pa.getEnd().toString()+"' and Dealed=0 ");
        sqlBuilder.append("  group by 'hello' ");
        NativeSqlQuery<ProjWarnCountAndLevelResult>nativeSqlQuery=new NativeSqlQueryImpl<>();
        List<ProjWarnCountAndLevelResult>result= nativeSqlQuery.query(pa.getProjID(),sqlBuilder.toString(),ProjWarnCountAndLevelResult.class);
        if(!CollectionUtil.isNullOrEmpty(result))
            result.get(0).setProjID(pa.getProjID());
        return result.size()>0?result.get(0):null;
    }

    public List<ProjWarnCountAndLevelResult>getAllProjWarnCountAndLevel(GetProjWarnCountAndLevelParameter pa)
    {
        List<ProjWarnCountAndLevelResult>result=new LinkedList<>();
        if(pa.getProjID()!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
        {
            ProjWarnCountAndLevelResult tempResult=getProjWarnCountAndLevel(pa);
            if(tempResult!=null)
                result.add(tempResult);
            return result;
        }
        CommonQueryHelper.getProjInfo().forEach(pi->{
            GetProjWarnCountAndLevelParameter tempPa=new GetProjWarnCountAndLevelParameter();
            tempPa.setProjID(pi.getId());
            tempPa.setBegin(pa.getBegin());
            tempPa.setEnd(pa.getEnd());
            ProjWarnCountAndLevelResult tempResult=getProjWarnCountAndLevel(tempPa);
            if(tempResult!=null)
                result.add(tempResult);
        });
        return result;
    }

    private void sendWarnHandlerMsg(final int projID)
    {
        CommonVariable.getExecutorService().execute(()->{
            try {
                TopicSender sender = TopicSender.getInstance(CommonVariable.getHandlerWarnTopicName());
                sender.sendWarnHandlerMsg(projID);
            }
            catch (Exception ex)
            {
                logger.error(ex.getMessage(),ex);
            }
        });
    }


}





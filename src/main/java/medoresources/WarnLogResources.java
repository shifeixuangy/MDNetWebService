package medoresources;

import commonUtil.AccessTokenPool;
import commonUtil.CommonVariable;
import commonUtil.LogUtil;
import data.common.LogType;
import data.common.ResultWrapper;
import data.common.TbLog;
import data.request.parameters.*;
import data.response.results.GetWarnLogListResultWarpper;
import data.response.results.ProjWarnCountAndLevelResult;
import data.response.results.WarnLogCountResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.WarnLogService;
import services.WarnValueService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/7.
 */
@Path("/")
public class WarnLogResources {
    private static final Log logger= LogFactory.getLog(WarnReviseResources.class);
    private WarnLogService wlService=new WarnLogService();
    @Path("/GetWarnLogList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper< GetWarnLogListResultWarpper>getWarnLogList(@NotNull @Valid GetWarnLogListParameter pa)
    {
        ResultWrapper<GetWarnLogListResultWarpper>result=null;
        try
        {
            result=ResultWrapper.success(wlService.getWarnLogList(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetWarnLogCountList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<WarnLogCountResult>>getWarnLogCountList(@NotNull @Valid GetWarnLogCountListParameter pa)
    {
        ResultWrapper<List<WarnLogCountResult>>result=null;
        try
        {
            result=ResultWrapper.success(wlService.getWarnLogCountList(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetProjWarnCountAndLevel")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<ProjWarnCountAndLevelResult>>getProjWarnCountAndLevel(@NotNull @Valid GetProjWarnCountAndLevelParameter pa)
    {
        ResultWrapper<List<ProjWarnCountAndLevelResult>>result=null;
        try
        {
            result=ResultWrapper.success(wlService.getAllProjWarnCountAndLevel(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/HasWarnLog")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Boolean>hasWarnLog(HasWarnLogParameter pa,@Context HttpHeaders headers)
    {
        ResultWrapper<Boolean>result=null;
        try
        {
            String accessToken=headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            result=ResultWrapper.success(wlService.hasWarnLog(accessToken,pa.getBegin(),pa.getEnd()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/DealWarnLog")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Boolean>dealWarnLog(@NotNull @Valid DealWarnLogParameter pa,@Context final HttpHeaders headers)
    {
        ResultWrapper<Boolean>result=null;
        try
        {
            String accessToken=headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            int uid= AccessTokenPool.getInstance().getUserInformation(accessToken).getUser().getId();
            wlService.dealWarnLog(pa,uid);
            LogUtil.writeDealWarnLog(accessToken,pa.getProjID(),pa.getNote());
            result=ResultWrapper.success(true);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.success(false);
        }
        return result;
    }
    @Path("/BatchDealWarnLog")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Boolean>batchDealWarnLog(BatchDealWarnLogParameter pa,@Context final HttpHeaders headers)
    {
        ResultWrapper<Boolean>result=null;
        try
        {
            String accessToken=headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            int uid= AccessTokenPool.getInstance().getUserInformation(accessToken).getUser().getId();
            wlService.batchDealWarnLog(pa, uid);
            LogUtil.writeDealWarnLog(accessToken,pa.getProjID(),pa.getNote());
            result=ResultWrapper.success(true);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.success(false);
        }
        return result;
    }




}







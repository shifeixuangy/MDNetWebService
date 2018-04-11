package medoresources;

import commonUtil.AccessTokenPool;
import commonUtil.CommonVariable;
import data.common.ErrCode;
import data.common.ResultWrapper;
import data.common.UserInformation;
import data.request.parameters.GetLogListParameter;
import data.response.results.GetLogListResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.LogService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * Created by liudongdong on 2015/5/10.
 */
@Path("/")
public class LogResources {
    private static final Log logger= LogFactory.getLog(LogResources.class);
    private LogService logService=new LogService();

    @Path("/GetLogList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<GetLogListResult>getLogList(@NotNull @Valid GetLogListParameter pa,@Context HttpHeaders headers)
    {
        ResultWrapper<GetLogListResult>result=null;
        try
        {
            String accessToken=headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            UserInformation userInfo= AccessTokenPool.getInstance().getUserInformation(accessToken);
            if(userInfo==null)
                return ResultWrapper.fail(ErrCode.ERR_POWER);
            if(!userInfo.isAdmin())
            {
                if((pa.getProjID()==CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)||(pa.getUid()==CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID))
                {
                    return ResultWrapper.fail(ErrCode.ERR_POWER);
                }
            }
            result=ResultWrapper.success(logService.getLogList(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
}

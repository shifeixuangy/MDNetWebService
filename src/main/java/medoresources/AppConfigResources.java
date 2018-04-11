package medoresources;

import commonUtil.AppConfig;
import commonUtil.CommonVariable;
import data.common.AccessType;
import data.common.ResultWrapper;
import data.common.TbUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * Created by liudongdong on 2016/1/18.
 */
@Path("/")
public class AppConfigResources {
    private  static final Log logger= LogFactory.getLog(AppConfigResources.class);

    @Path("/GetAppName")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> getAppName(@Context HttpHeaders httpHeaders)
    {
        AccessType accessType=AccessType.valueOf(httpHeaders.getHeaderString("access_type").toUpperCase());
        return ResultWrapper.<String>success(AppConfig.getAppName(accessType));
    }

    @Path("/GetBG")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> getBG(@Context HttpHeaders httpHeaders)
    {
        AccessType accessType=AccessType.valueOf(httpHeaders.getHeaderString("access_type").toUpperCase());
        return ResultWrapper.<String>success(AppConfig.getBG(accessType));
    }

    @POST
    @Path("/SetAppName")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>setAppName(String appName,@Context HttpHeaders httpHeaders)
    {
        AccessType accessType=AccessType.valueOf(httpHeaders.getHeaderString("access_type").toUpperCase());
        ResultWrapper result=null;
        try {
            AppConfig.setAppName(appName,accessType);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/SetBG")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>setBG(String bg,@Context HttpHeaders httpHeaders)
    {
        AccessType accessType=AccessType.valueOf(httpHeaders.getHeaderString("access_type").toUpperCase());
        ResultWrapper result=null;
        try {
            AppConfig.setBG(bg,accessType);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

}

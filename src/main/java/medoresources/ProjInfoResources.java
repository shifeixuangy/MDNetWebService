package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbProjInfo;
import data.response.results.GetProjListResult;
import data.response.results.GetProjWarnNumberListResult;
import data.response.results.GetSensorExtremeDataResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.ProjInfoService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/5.
 */
@Path("/")
public class ProjInfoResources {
    private static final Log logger= LogFactory.getLog(ProjInfoResources.class);
    private ProjInfoService piService=new ProjInfoService();
    @Path("/AddProjInfo")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addProjInfo(@NotNull @Valid TbProjInfo pi)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(piService.addProjInfo(pi));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/UpdateProjInfo")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateProjInfo(@NotNull @Valid  TbProjInfo pi)
    {
        ResultWrapper<String>result=null;
        try
        {
            piService.updateProjInfo(pi);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteProjInfo")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteProjInfo(int projID)
    {
        ResultWrapper<String>result=null;
        try
        {
            piService.deleteProjInfo(projID);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/GetProjList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetProjListResult>>getProjList(@Context final HttpHeaders headers,int projID)
    {
        ResultWrapper<List<GetProjListResult>>result=null;
        try
        {
            String accessToken=headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            result=ResultWrapper.success(piService.getProjList(accessToken,projID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetProjWarnNumberList")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper< List<GetProjWarnNumberListResult>>getProjWarnNumberList(@Context final HttpHeaders headers)
    {
        ResultWrapper<List<GetProjWarnNumberListResult>>result=null;
        try
        {
            String accessToken=headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            result=ResultWrapper.success(piService.getProjWarnNumberList(accessToken));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }



}















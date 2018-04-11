package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbWarnValue;
import data.request.parameters.AddAndUpdateWarnValueParameter;
import data.request.parameters.DeleteWarnValueParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.WarnValueService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/6.
 */
@Path("/")
public class WarnValueResources {
    private static Log logger= LogFactory.getLog(WarnValueResources.class);
    private WarnValueService wvService=new WarnValueService();
    @Path("/GetWarnValueList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbWarnValue>>getWarnValueList(int projID)
    {
        ResultWrapper<List<TbWarnValue>>result=null;
        try
        {
            result=ResultWrapper.success(wvService.getWarnValueList(projID));
        }
        catch (Exception ex)
        {
            logger.error(ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/AddWarnValue")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>addWarnValue(@NotNull @Valid AddAndUpdateWarnValueParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wvService.addWarnValue(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/UpdateWarnValue")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateWarnValue(@NotNull @Valid AddAndUpdateWarnValueParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wvService.updateWarnValue(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/DeleteWarnValue")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteWarnValue(@NotNull @Valid DeleteWarnValueParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wvService.deleteWarnValue(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

}













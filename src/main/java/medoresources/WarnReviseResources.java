package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.request.parameters.AddAndUpdateWarnReviseParameter;
import data.request.parameters.DeleteWarnReviseParameter;
import data.response.results.WarnReviseResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.WarnReviseService;

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
public class WarnReviseResources {
    private static Log logger= LogFactory.getLog(WarnReviseResources.class);
    private WarnReviseService wrService=new WarnReviseService();
    @Path("/GetWarnReviseList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<WarnReviseResult>>getWarnReviseList(int projID)
    {
        ResultWrapper< List<WarnReviseResult>>results=null;
        try
        {
            results=ResultWrapper.success(wrService.getWarnReviseList(projID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            results=ResultWrapper.serverException(ex.getMessage());
        }
        return  results;
    }

    @Path("/AddWarnRevise")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>addWarnRevise(@NotNull @Valid AddAndUpdateWarnReviseParameter pa)
    {
        ResultWrapper<String>results=null;
        try
        {
            wrService.addWarnRevise(pa);
            results=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            results=ResultWrapper.serverException(ex.getMessage());
        }
        return  results;
    }

    @Path("/UpdateWarnRevise")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateWarnRevise(@NotNull @Valid AddAndUpdateWarnReviseParameter pa)
    {
        ResultWrapper<String>results=null;
        try
        {
            wrService.updateWarnRevise(pa);
            results=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            results=ResultWrapper.serverException(ex.getMessage());
        }
        return  results;
    }

    @Path("/DeleteWarnRevise")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteWarnRevise(@NotNull @Valid DeleteWarnReviseParameter pa)
    {
        ResultWrapper<String>results=null;
        try
        {
            wrService.deleteWarnRevise(pa);
            results=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            results=ResultWrapper.serverException(ex.getMessage());
        }
        return  results;
    }



}



















package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbProjManager;
import data.request.parameters.DeleteProjMgrParameter;
import data.response.results.GetProjMgrListResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.ProjMgrService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
@Path("/")
public class ProjMgrResources {
    private static final Log logger= LogFactory.getLog(ProjMgrResources.class);
    private ProjMgrService pmService=new ProjMgrService();
    @Path("/GetProjMgrList")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetProjMgrListResult>>getProjMgrList()
    {
        ResultWrapper<List<GetProjMgrListResult>>result=null;
        try
        {
            result=ResultWrapper.success(pmService.getProjMgrList());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @Path("/AddProjMgr")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> addProjMgr(@NotNull @Valid TbProjManager pm)
    {
        ResultWrapper<String>result=null;
        try
        {
            pmService.addProjMgr(pm);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @Path("/UpdateProjMgr")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateProjMgr(@NotNull @Valid TbProjManager pm)
    {
        ResultWrapper<String>result=null;
        try
        {
            pmService.updateProjMgr(pm);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @Path("/DeleteProjMgr")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> deleteProjMgr(@NotNull @Valid DeleteProjMgrParameter pm)
    {
        ResultWrapper<String>result=null;
        try
        {
            pmService.deleteProjMgr(pm);
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


















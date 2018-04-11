package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbRegionManager;
import data.request.parameters.DeleteRegionMgrParameter;
import data.response.results.GetRegionMgrListResult;
import dataDAO.ExecuteSql;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.RegionMgrService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
@Path("/")
public class RegionMgrResources {
    private static Log logger= LogFactory.getLog(RegionMgrResources.class);
    private RegionMgrService rmService=new RegionMgrService();

    @Path("/GetRegionMgrList")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetRegionMgrListResult>>getRegionMgrList()
    {
        ResultWrapper<List<GetRegionMgrListResult>>result=null;
        try
        {
            result=ResultWrapper.success(rmService.getRegionMgrList());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @Path("/AddRegionMgr")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>addRegionMgr(@NotNull @Valid TbRegionManager rm)
    {
        ResultWrapper<String>result=null;
        try
        {
            rmService.addRegionMgr(rm);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @Path("/UpdateRegionMgr")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateRegionMgr(@NotNull @Valid TbRegionManager rm)
    {
        ResultWrapper<String>result=null;
        try
        {
            rmService.updateRegionMgr(rm);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/DeleteRegionMgr")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteRegionMgr(@NotNull @Valid DeleteRegionMgrParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            rmService.deleteRegionMgr(pa);
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












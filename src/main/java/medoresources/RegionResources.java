package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbRegion;
import data.request.parameters.AddRegionParameter;
import data.request.parameters.UpdateRegionParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.RegionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
@Path("/")
public class RegionResources {
    private static final Log logger= LogFactory.getLog(RegionResources.class);
    private RegionService reService=new RegionService();
    @Path("/GetRegionList")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbRegion>>getRegionList()
    {
        ResultWrapper<List<TbRegion>>result=null;
        try
        {
            result=ResultWrapper.success(reService.getRegionList());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/GetRegion")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<TbRegion>getRegion(int regionID)
    {
        ResultWrapper<TbRegion>result=null;
        try
        {
            result=ResultWrapper.success(reService.getRegion(regionID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/AddRegion")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>addRegion(@NotNull @Valid AddRegionParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            reService.addRegion(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/UpdateRegion")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateRegion(@NotNull @Valid UpdateRegionParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            reService.updateRegion(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteRegion")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteRegion(int regionID)
    {
        ResultWrapper<String>result=null;
        try
        {
            reService.deleteRegion(regionID);
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













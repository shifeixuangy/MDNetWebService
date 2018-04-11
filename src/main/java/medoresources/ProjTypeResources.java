package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbProjType;
import data.common.TbRegion;
import data.request.parameters.AddRegionParameter;
import data.request.parameters.UpdateRegionParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.ProjTypeService;
import services.RegionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by admin on 2016/6/13.
 */
@Path("/")
public class ProjTypeResources {
    private static final Log logger= LogFactory.getLog(ProjTypeResources.class);
    private ProjTypeService ptService=new ProjTypeService();

    @Path("/GetProjTypeList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbProjType>> getProjTypeList(int projTypeID)
    {
        ResultWrapper<List<TbProjType>>result=null;
        try
        {
            result=ResultWrapper.success(ptService.getProjTypes(projTypeID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/AddProjType")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addProjType(@NotNull @Valid TbProjType pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            Integer id=ptService.addProjType(pa);
            result=ResultWrapper.success(id);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/UpdateProjType")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateProjType(@NotNull @Valid TbProjType pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            ptService.updateProjType(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteProjType")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteProjType(int projTypeID)
    {
        ResultWrapper<String>result=null;
        try
        {
            ptService.deleteProjType(projTypeID);
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

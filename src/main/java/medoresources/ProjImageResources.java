package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbProjInfo;
import data.request.parameters.AddProjImageParameter;
import data.request.parameters.DeleteProjImageParameter;
import data.request.parameters.GetProjImageParameter;
import data.response.results.GetProjListResult;
import data.response.results.ProjImageWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.ProjImageService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

/**
 * Created by liudongdong on 2016/1/14.
 */
@Path("/")
public class ProjImageResources {
    private static final Log logger= LogFactory.getLog(ProjImageResources.class);
    private ProjImageService pis=new ProjImageService();

    @Path("/AddProjImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer> addProjImage(@NotNull AddProjImageParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(pis.addProjImage(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/DeleteProjImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteProjImage(DeleteProjImageParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            pis.deleteProjImage(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetProjImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<ProjImageWrapper>>getProjImage(GetProjImageParameter pa)
    {
        ResultWrapper<List<ProjImageWrapper>>result=null;
        try
        {
            result=ResultWrapper.success(pis.getProjImage(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
}

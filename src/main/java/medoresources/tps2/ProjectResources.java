package medoresources.tps2;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbGroup;
import data.proj.TbTps2KonwnPoint;
import data.proj.TbTps2Project;
import data.request.parameters.*;
import medoresources.GroupResources;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.GroupService;
import services.Tps2KonwPointService;
import services.Tps2ProjectService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by admin on 2016/4/25.
 */
@Path("/")
public class ProjectResources {
    private static final Log logger= LogFactory.getLog(ProjectResources.class);
    private Tps2ProjectService ps=new Tps2ProjectService();

    @Path("/GetTps2Project")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbTps2Project>> getTps2Project(GetAndDeleteTps2ProjectParameter pa)
    {
        ResultWrapper<List<TbTps2Project>>result=null;
        try
        {
            result=ResultWrapper.success(ps.getTps2ProjectList(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/AddTps2Project")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addTps2Project(@NotNull @Valid AddAndUpdateTp2ProjectParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(ps.addTps2Project(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/UpdateTps2Project")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateTps2Project(@NotNull @Valid AddAndUpdateTp2ProjectParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            ps.updateTps2Project(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteTps2Project")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteTps2Project(@NotNull @Valid GetAndDeleteTps2ProjectParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            ps.deleteTps2Project(pa);
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

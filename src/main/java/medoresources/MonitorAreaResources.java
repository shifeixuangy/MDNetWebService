package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbMonitorArea;
import data.request.parameters.AddMonitorAreaParameter;
import data.request.parameters.DeleteMonitorAreaParameter;
import data.request.parameters.UpdateMonitorAreaParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.MonitorAreaService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
@Path("/")
public class MonitorAreaResources {
    private static final Log logger= LogFactory.getLog(MonitorAreaResources.class);
    private MonitorAreaService maService=new MonitorAreaService();


    @POST
    @Path("/GetMonitorAreaList")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbMonitorArea>>getMonitorAreaList(int projID)
    {
        ResultWrapper<List<TbMonitorArea>>result=null;
        try
        {
            result=ResultWrapper.success(maService.getMonitorAreaList(projID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @POST
    @Path("/AddMonitorArea")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addMonitorArea(@NotNull @Valid AddMonitorAreaParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(maService.addMonitorArea(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @POST
    @Path("/UpdateMonitorArea")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateMonitorArea(@NotNull @Valid UpdateMonitorAreaParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            maService.updateMonitorArea( pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @POST
    @Path("/DeleteMonitorArea")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteMonitorArea(@NotNull @Valid DeleteMonitorAreaParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            maService.deleteMonitorArea( pa);
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















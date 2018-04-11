package medoresources.tps2;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbGroup;
import data.proj.TbTps2KonwnPoint;
import data.request.parameters.*;
import medoresources.GroupResources;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.GroupService;
import services.Tps2KonwPointService;

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
public class KnowPointResources {
    private static final Log logger= LogFactory.getLog(KnowPointResources.class);
    private Tps2KonwPointService kps=new Tps2KonwPointService();
    @Path("/GetTps2KonwPoint")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbTps2KonwnPoint>> getTps2KonwPoint(GetAndDeleteTps2KnowPointParameter pa)
    {
        ResultWrapper<List<TbTps2KonwnPoint>>result=null;
        try
        {
            result=ResultWrapper.success(kps.getKonwPointList(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/AddTps2KonwPoint")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addTps2KonwPoint(@NotNull @Valid AddAndUpdateTps2KnowPointParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(kps.addTps2KonwPoint(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/UpdateTps2KonwPoint")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateTps2KonwPoint(@NotNull @Valid AddAndUpdateTps2KnowPointParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            kps.updateTps2KonwPoint(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteTps2KonwPoint")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteTps2KonwPoint(@NotNull @Valid GetAndDeleteTps2KnowPointParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            kps.deleteTps2KonwPoint(pa);
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

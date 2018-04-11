package medoresources.tps2;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbGroup;
import data.proj.TbTps2KonwnPoint;
import data.proj.TbTps2ObserverStation;
import data.request.parameters.*;
import medoresources.GroupResources;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.GroupService;
import services.Tps2KonwPointService;
import services.Tps2ObserverStationService;

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
public class ObserverStationResources {
    private static final Log logger= LogFactory.getLog(ObserverStationResources.class);
    private Tps2ObserverStationService oss=new Tps2ObserverStationService();

    @Path("/GetTps2ObserverStation")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbTps2ObserverStation>> getTps2ObserverStation(GetAndDeleteTps2ObserverStationParameter pa)
    {
        ResultWrapper<List<TbTps2ObserverStation>>result=null;
        try
        {
            result=ResultWrapper.success(oss.getObserverStationList(pa.getProjID(),pa.getId()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/AddTps2ObserverStation")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addTps2ObserverStation(@NotNull @Valid AddAndUpdateTps2ObserverStationParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(oss.addObserverStation(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/UpdateTps2ObserverStation")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateTps2ObserverStation(@NotNull @Valid AddAndUpdateTps2ObserverStationParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            oss.updateObserverStation(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteTps2ObserverStation")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteTps2ObserverStation(@NotNull @Valid GetAndDeleteTps2ObserverStationParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            oss.deleteObserverStation(pa);
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

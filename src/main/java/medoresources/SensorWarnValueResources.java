package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbSensorWarnValue;
import data.proj.TbWarnValue;
import data.request.parameters.*;
import data.response.results.GetSensorWarnValueListResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.SensorWarnValueService;
import services.WarnValueService;

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
public class SensorWarnValueResources {
    private static Log logger= LogFactory.getLog(SensorWarnValueResources.class);
    private SensorWarnValueService wvService=new SensorWarnValueService();
    @Path("/GetSensorWarnValueList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetSensorWarnValueListResult>>getSensorWarnValueList(GetSensorWarnValueListParameter pa)
    {
        ResultWrapper<List<GetSensorWarnValueListResult>>result=null;
        try
        {
            result=ResultWrapper.success(wvService.getSensorWarnValueList(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/AddSensorWarnValue")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addSensorWarnValue(@NotNull @Valid AddAndUpdateSensorWarnValueParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
//            wvService.addWarnValue(pa);
            result=ResultWrapper.success(wvService.addSensorWarnValue(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/UpdateSensorWarnValue")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateSensorWarnValue(@NotNull @Valid AddAndUpdateSensorWarnValueParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wvService.updateSensorWarnValue(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/DeleteSensorWarnValue")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteSensorWarnValue(@NotNull @Valid DeleteSensorWarnValueParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wvService.deleteSensorWarnValue(pa);
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













package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.request.parameters.DeleteSensorDataRecordParameter;
import data.request.parameters.UpdateRegionParameter;
import data.request.parameters.UpdateSensorDataRecordParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.SensorDataOperationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by liudongdong on 2016/2/18.
 */
@Path("/")
public class SensorDataOperationResources {
    private static final Log logger= LogFactory.getLog(SensorDataOperationResources.class);
    private SensorDataOperationService operationService=new SensorDataOperationService();

    @Path("/UpdateSensorDataRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateSensorDataRecord(@NotNull @Valid UpdateSensorDataRecordParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            operationService.updateSensorDataRecord(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/DeleteSensorDataRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteSensorDataRecord(DeleteSensorDataRecordParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            operationService.deleteSensorDataRecord(pa);
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

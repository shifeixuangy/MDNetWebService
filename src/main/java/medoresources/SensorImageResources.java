package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbProjInfo;
import data.request.parameters.*;
import data.response.results.GetProjListResult;
import data.response.results.ProjImageWrapper;
import data.response.results.SensorImageWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.ProjImageService;
import services.SensorImageService;

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
public class SensorImageResources {
    private static final Log logger= LogFactory.getLog(SensorImageResources.class);
    private SensorImageService sis=new SensorImageService();

    @Path("/AddSensorImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer> addSensorImage(@NotNull AddSensorImageParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(sis.addSensorImage(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/DeleteSensorImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteSensorImage(DeleteSensorImageParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            sis.deleteSensorImage(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetSensorImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<SensorImageWrapper>>getSensorImage(GetSensorImageParameter pa)
    {
        ResultWrapper<List<SensorImageWrapper>>result=null;
        try
        {
            result=ResultWrapper.success(sis.getSensorImage(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
}

package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.SensorData;
import data.proj.TbSensorAttri;
import data.request.parameters.*;
import data.response.results.GetSensorExtremeDataResult;
import data.response.results.SensorResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.SensorServices;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/4.
 */
@Path("/")
public class SensorResources {
    private static Log logger= LogFactory.getLog(SensorResources.class);
    private SensorServices sensorServices=new SensorServices();

    @Path("/AddSensor")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addSensor(@NotNull @Valid SensorAttriParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            Integer id= sensorServices.addSensor(pa);
            result=ResultWrapper.success(id);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/UpdateSensor")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateSensor(@NotNull @Valid SensorAttriParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            sensorServices.updateSensor(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/DeleteSensor")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteSensor(@NotNull @Valid DeleteSensorParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            sensorServices.deleteSensor(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetSensorDataRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<Object>>getSensorDataRecord(@NotNull @Valid GetSensorDataRecordParameter pa)
    {
        ResultWrapper<List<Object>>result=null;
        try
        {
            List<Object>datas=sensorServices.getSensorDataRecord(pa);
            result=ResultWrapper.success(datas);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetSensorDataRecordEx")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<Object>>getSensorDataRecordEx(@NotNull @Valid GetSensorDataRecordExParameter pa)
    {
        ResultWrapper<List<Object>>result=null;
        try
        {
            long pre= System.currentTimeMillis();
            List<Object>datas=sensorServices.getSensorDataRecordEx(pa);
            result=ResultWrapper.success(datas);
            logger.info("获取数据完毕，共计耗时："+(System.currentTimeMillis()-pre));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/GetSensorExtremeData")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetSensorExtremeDataResult>>getSensorExtremeData(GetSensorExtremeDataParameter pa)
    {
        ResultWrapper<List<GetSensorExtremeDataResult>>result=null;
        try
        {
            List<GetSensorExtremeDataResult>datas=sensorServices.getsensorExtremeData(pa);
            result=ResultWrapper.success(datas);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }



    @Path("/GetSensor")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<SensorResult>getSensor(@NotNull @Valid GetSensorParameter pa)
    {
        ResultWrapper<SensorResult>result=null;
        try
        {
            result= ResultWrapper.success(sensorServices.getSensor(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/GetSensorList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<SensorResult>>getSensorList(@NotNull @Valid GetSensorListParameter pa)
    {
        ResultWrapper<List<SensorResult>>result=null;
        try
        {
            result= ResultWrapper.success(sensorServices.getSensorList(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/GetGroupSensorList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbSensorAttri>>getGroupSensorList(@NotNull @Valid GetGroupSensorParameter pa)
    {
        ResultWrapper<List<TbSensorAttri>>result=null;
        try
        {
            result= ResultWrapper.success(sensorServices.getGroupSensorList(pa.getProjID(),pa.getGroupID()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/GetGroupSensorNewData")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<SensorData>>getGroupSensorNewData(@NotNull @Valid GetGroupSensorParameter pa)
    {
        ResultWrapper<List<SensorData>>result=null;
        try
        {
            result= ResultWrapper.success(sensorServices.getGroupSensorNewData(pa.getProjID(),pa.getGroupID()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }


}

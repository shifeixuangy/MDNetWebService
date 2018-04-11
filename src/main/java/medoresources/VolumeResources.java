package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbGroup;
import data.proj.TbVolume;
import data.request.parameters.AddAndUpdateVolumeParameter;
import data.request.parameters.DeleteVolumeParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.VolumeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;


/**
 * Created by liudongdong on 2015/9/8.
 */
@Path("/")
public class VolumeResources {
    private static Log logger= LogFactory.getLog(VolumeResources.class);
    private VolumeService vs=new VolumeService();
    @Path("/GetVolumeList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbVolume>>getVolumeList(int projID)
    {
        ResultWrapper<List<TbVolume>>result=null;
        try
        {
            result=ResultWrapper.success(vs.getVolumes(projID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/AddVolume")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer> addVolume(AddAndUpdateVolumeParameter pa)
    {
        ResultWrapper<Integer> result=null;
        try
        {
            result=ResultWrapper.success(vs.addVolume(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/UpdateVolume")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateVolume(AddAndUpdateVolumeParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            vs.updateVolume(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/DeleteVolume")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteVolume(DeleteVolumeParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            vs.deleteVolume(pa);
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

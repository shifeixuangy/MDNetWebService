package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.CheckImage;
import data.proj.CheckVideo;
import data.request.parameters.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.CheckImageService;
import services.CheckVideoService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/11/4.
 */
@Path("/")
public class CheckVideoResources {
    private  static final Log logger= LogFactory.getLog(AdminResources.class);
    private CheckVideoService vs=new CheckVideoService();

    @Path("/GetCheckVideo")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<CheckVideo>> getCheckVideo(GetCheckVideoParameter pa)
    {
        ResultWrapper<List<CheckVideo>>result=null;
        try
        {
            result=ResultWrapper.success(vs.getVideo(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/AddCheckVideo")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addCheckVideo(@NotNull @Valid AddCheckVideoParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(vs.addVideo(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/DeleteCheckVideo")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteCheckVideo(@NotNull @Valid DeleteCheckVideoParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            vs.deleteVideo(pa);
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

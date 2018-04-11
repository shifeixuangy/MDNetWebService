package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.CheckImage;
import data.proj.CheckPerson;
import data.request.parameters.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.CheckImageService;
import services.CheckPersonService;

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
public class CheckImageResources {
    private  static final Log logger= LogFactory.getLog(AdminResources.class);
    private CheckImageService is=new CheckImageService();

    @Path("/GetCheckImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<CheckImage>> getCheckImage(GetCheckImageParameter pa)
    {
        ResultWrapper<List<CheckImage>>result=null;
        try
        {
            result=ResultWrapper.success(is.getImage(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/AddCheckImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addCheckImage(@NotNull @Valid AddCheckImageParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(is.addImage(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/DeleteCheckImage")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteCheckImage(@NotNull @Valid DeleteCheckImageParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            is.deleteImage(pa);
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

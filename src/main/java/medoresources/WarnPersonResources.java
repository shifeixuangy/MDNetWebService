package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbWarnPerson;
import data.request.parameters.AddAndUpdateWarnPersonParameter;
import data.request.parameters.DeleteWarnPersonParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.WarnPersonService;

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
public class WarnPersonResources {
    private static final Log logger= LogFactory.getLog(WarnPersonResources.class);
    private WarnPersonService wpService=new WarnPersonService();
    @POST
    @Path("/GetWarnPersonList")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbWarnPerson>>getWarnPersonList(int projID)
    {
        ResultWrapper<List<TbWarnPerson>>result=null;
        try
        {
            result=ResultWrapper.success(wpService.getWarnPersonList(projID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @POST
    @Path("/AddWarnPerson")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> addWarnPerson(@NotNull @Valid AddAndUpdateWarnPersonParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wpService.addWarnPerson( pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/UpdateWarnPerson")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateWarnPerson(@NotNull @Valid AddAndUpdateWarnPersonParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wpService.updateWarnPerson(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/DeleteWarnPerson")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> deleteWarnPerson(@NotNull @Valid DeleteWarnPersonParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            wpService.deleteWarnPerson(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }


}














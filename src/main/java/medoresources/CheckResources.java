package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.Check;
import data.proj.TbGroup;
import data.request.parameters.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.CheckService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/11/2.
 */
@Path("/")
public class CheckResources {
    private  static final Log logger= LogFactory.getLog(AdminResources.class);
    private CheckService cs=new CheckService();

    @Path("/GetCheck")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<Check>>getCheck(GetAndDeleteCheckParameter pa)
    {
        ResultWrapper<List<Check>>result=null;
        try
        {
            result=ResultWrapper.success(cs.getCheck(pa.getProjID(),pa.getCheckID()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/AddCheck")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addCheck(@NotNull @Valid AddAndUpdateCheckParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(cs.add(pa.getProjID(),pa.getCheck()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/UpdateCheck")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateCheck(@NotNull @Valid AddAndUpdateCheckParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            cs.update(pa.getProjID(),pa.getCheck());
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteCheck")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteCheck(@NotNull @Valid GetAndDeleteCheckParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            cs.delete(pa.getProjID(),pa.getCheckID());
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

package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.CheckPerson;
import data.request.parameters.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class CheckPersonResources {
    private  static final Log logger= LogFactory.getLog(AdminResources.class);
    private CheckPersonService ps=new CheckPersonService();

    @Path("/GetCheckPerson")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<CheckPerson>> getCheckPerson(GetCheckPersonParameter pa)
    {
        ResultWrapper<List<CheckPerson>>result=null;
        try
        {
            result=ResultWrapper.success(ps.getCheckPerson(pa.getProjID(),pa.getCheckID(),pa.getUserID()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/AddCheckPerson")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addCheckPerson(@NotNull @Valid AddCheckPersonParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(ps.addCheckPerson(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/DeleteCheckPerson")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteCheckPerson(@NotNull @Valid DeleteCheckPersonParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            ps.deleteCheckPerson(pa);
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

package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.Record;
import data.request.parameters.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.RecordService;

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
public class RecordResources {
    private  static final Log logger= LogFactory.getLog(AdminResources.class);
    private RecordService rs=new RecordService();

    @Path("/GetRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<Record>>getRecord(GetAndDeleteRecordParameter pa)
    {
        ResultWrapper<List<Record>>result=null;
        try
        {
            result=ResultWrapper.success(rs.getRecord(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }


    @Path("/AddRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addRecord(@NotNull @Valid AddAndUpdateRecordParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(rs.add(pa.getProjID(),pa.getRecord()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }

    @Path("/UpdateRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateRecord(@NotNull @Valid AddAndUpdateRecordParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            rs.update(pa.getProjID(),pa.getRecord());
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteRecord(@NotNull @Valid GetAndDeleteRecordParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            rs.delete(pa.getProjID(),pa.getRecordID());
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

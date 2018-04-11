package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbTpsData;
import data.request.parameters.AddTPSDataRequestParameter;
import data.request.parameters.GetMinTimeTPSDataParameter;
import data.response.results.GetProjUserListResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.TPSService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/16.
 */
@Path("/")
public class TPSResources {
    private static Log logger= LogFactory.getLog(TPSResources.class);
    private TPSService ts=new TPSService();

    @POST
    @Path("/GetMinTimeTPSData")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbTpsData>> getMinTimeTPSData(GetMinTimeTPSDataParameter pa)
    {
        ResultWrapper<List<TbTpsData>> result=null;
        try {
            result=ResultWrapper.success(ts.getMinTimeData(pa.getProjID(),pa.getSensorID()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/AddTPSData")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> addTPSData(AddTPSDataRequestParameter pa)
    {
        ResultWrapper<String> result=null;
        try {
            ts.adddTPSData(pa);
            result=ResultWrapper.success("");
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
}

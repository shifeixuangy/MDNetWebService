package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbDs2Data;
import data.proj.TbTpsData;
import data.request.parameters.AddTPSDataRequestParameter;
import data.request.parameters.GetMinTimeDS2DataParameter;
import data.request.parameters.GetMinTimeTPSDataParameter;
import data.response.results.GetProjUserListResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.DS2Service;
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
public class DS2Resources {
    private static Log logger= LogFactory.getLog(DS2Resources.class);
    private DS2Service ts=new DS2Service();

    @POST
    @Path("/GetMinTimeDS2Data")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbDs2Data>> getMinTimeDS2Data(GetMinTimeDS2DataParameter pa)
    {
        ResultWrapper<List<TbDs2Data>> result=null;
        try {
            result=ResultWrapper.success(ts.getMinTimeDS2Data(pa.getProjID(),pa.getSensorID()));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

}

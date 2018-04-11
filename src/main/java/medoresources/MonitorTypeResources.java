package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbMonitorType;
import data.request.parameters.GetMonitorTypeListParameter;
import data.request.parameters.UpdateMonitorTypeParameter;
import data.response.results.GetMonitorTypeListResult;
import dataDAO.ProjQueryHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.MonitorTypeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
@Path("/")
public class MonitorTypeResources {
    private static final Log logger = LogFactory.getLog(MonitorTypeResources.class);
    private MonitorTypeService mtService = new MonitorTypeService();

    @Path("/GetMonitorTypeList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetMonitorTypeListResult>> getMonitorTypeList(GetMonitorTypeListParameter pa) {
        ResultWrapper<List<GetMonitorTypeListResult>> result = null;
        try {
            result = ResultWrapper.success(mtService.getMonitorTypeList(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/UpdateMonitorType")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateMonitorType(UpdateMonitorTypeParameter pa) {
        ResultWrapper<String> result = null;
        try {
            mtService.updateMonitorType(pa);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
}

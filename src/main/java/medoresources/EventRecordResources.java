package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbEventRecord;
import data.proj.TbGroup;
import data.request.parameters.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.EventRecordService;
import services.GroupService;

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
public class EventRecordResources {
    private static final Log logger = LogFactory.getLog(GroupResources.class);
    private EventRecordService es = new EventRecordService();

    @Path("/GetEventRecordList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbEventRecord>> getEventRecordList(GetEventRecordListParameter pa) {
        ResultWrapper<List<TbEventRecord>> result = null;
        try {
            result = ResultWrapper.success(es.getEventRecordList(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/AddEventRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer> addEventRecord(@NotNull @Valid AddAndUpdateEventRecordParameter pa) {
        ResultWrapper<Integer> result = null;
        try {
            result = ResultWrapper.success(es.addEventRecord(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/UpdateEventRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateEventRecord(@NotNull @Valid AddAndUpdateEventRecordParameter pa) {
        ResultWrapper<String> result = null;
        try {
            es.updateEventRecord(pa);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/DeleteEventRecord")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> deleteEventRecord(@NotNull @Valid DeleteEventRecordParameter pa) {
        ResultWrapper<String> result = null;
        try {
            es.deleteEventRecord(pa);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

}














package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbUser;
import data.proj.TbChannel;
import data.proj.TbChannelData;
import data.request.parameters.*;
import data.response.results.GetChannelFileListResult;
import data.response.results.GetProjUserListResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.AdminService;
import services.ChannelService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/6.
 */
@Path("/")
public class ChannelResources {
    private static final Log logger = LogFactory.getLog(AdminResources.class);
    private ChannelService cs = new ChannelService();

    @POST
    @Path("/AddChannel")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer> addChannel(@NotNull @Valid AddAndUpdateChannelParameter pa) {
        ResultWrapper result = null;
        try {
            result = ResultWrapper.success(cs.addChannel(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/UpdateChannel")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateChannel(@NotNull @Valid AddAndUpdateChannelParameter pa) {
        ResultWrapper result = null;
        try {
            cs.updateChannel(pa);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/DeleteChannel")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> deleteChannel(DeleteChannelParameter pa) {
        ResultWrapper<String> result = null;
        try {
            cs.deleteChannel(pa);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/GetChannelList")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbChannel>> getChannelList(GetChannelListParameter pa) {
        ResultWrapper<List<TbChannel>> result = null;
        try {
            result = ResultWrapper.success(cs.getChannelList(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

//    @POST
//    @Path("/AddChannelData")
//    @Produces(CommonVariable.JSON_WITH_UTF8)
//    @Consumes(CommonVariable.JSON_WITH_UTF8)
//    public ResultWrapper<Integer> addChannelData(@NotNull @Valid AddChannelDataParameter pa) {
//        ResultWrapper result = null;
//        try {
//            result = ResultWrapper.success(cs.addChannelData(pa));
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//            result = ResultWrapper.serverException(ex.getMessage());
//        }
//        return result;
//    }

    @POST
    @Path("/AddData")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> addData(@NotNull @Valid AddDataParameter pa) {
        ResultWrapper<String> result = null;
        try {
            long pre=System.currentTimeMillis();
            cs.addData(pa);
            result = ResultWrapper.success("");
            long now=System.currentTimeMillis();
            logger.info("AddData添加数据完毕，总计耗时："+Long.toString(now-pre)+" ms");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/AddDataDebug")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<AddDataParameter> addDataDebug(@NotNull @Valid AddDataParameter pa) {
       return ResultWrapper.success(pa);
    }

    @POST
    @Path("/GetChannelDataList")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbChannelData>> getChannelDataList(GetChannelDataListParameter pa) {
        ResultWrapper<List<TbChannelData>> result = null;
        try {
            result = ResultWrapper.success(cs.getChannelDataList(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/GetChannelFileList")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetChannelFileListResult>> getChannelFileList(GetChannelFileListParameter pa) {
        ResultWrapper<List<GetChannelFileListResult>> result = null;
        try {
            result = ResultWrapper.success(cs.getChannelFileList(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/GetChannelDataListByFile")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbChannelData>> getChannelDataListByFile(GetChannelDataListByFileParameter pa) {
        ResultWrapper<List<TbChannelData>> result = null;
        try {
            result = ResultWrapper.success(cs.getChannelDataListByFile(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/GetChannelDataAccelerListByFile")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbChannelData>> getChannelDataAccelerListByFile(GetChannelDataListByFileParameter pa) {
        ResultWrapper<List<TbChannelData>> result = null;
        try {
            long pre=System.currentTimeMillis();
            result = ResultWrapper.success(cs.getChannelDataAccelerListByFile(pa));
            long now=System.currentTimeMillis();
            logger.info("GetChannelDataAccelerListByFile获取数据完毕，总计耗时："+Long.toString(now-pre)+" ms");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
}

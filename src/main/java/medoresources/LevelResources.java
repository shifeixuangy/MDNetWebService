package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbLevel;
import data.response.results.LevelProjWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.LevelService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

/**
 * Created by liudongdong on 2016/3/22.
 */
@Path("/")
public class LevelResources {
    private static final Log logger = LogFactory.getLog(LevelResources.class);
    private LevelService ls = new LevelService();


    @Path("/GetLevelProjList")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<LevelProjWrapper>> getLevelProjList(@Context final HttpHeaders headers) {
        ResultWrapper<List<LevelProjWrapper>> result = null;
        try {
            String accessToken = headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            result = ResultWrapper.success(ls.getLevelProjList(accessToken));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/GetLevelList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbLevel>> getLevelList(int fatherLevelID) {
        ResultWrapper<List<TbLevel>> result = null;
        try {
            result = ResultWrapper.success(ls.getLevelList(fatherLevelID));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/AddLevel")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer> addLevel(@NotNull @Valid TbLevel pa) {
        ResultWrapper<Integer> result = null;
        try {
            result = ResultWrapper.success(ls.addLevel(pa));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/UpdateLevel")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateLevel(@NotNull @Valid TbLevel pa) {
        ResultWrapper<String> result = null;
        try {
            ls.updateLevel(pa);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/DeleteLevel")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> deleteLevel(int levelID) {
        ResultWrapper<String> result = null;
        try {
            ls.deleteLevel(levelID);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }


}

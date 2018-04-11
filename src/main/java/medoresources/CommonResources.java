package medoresources;

import commonUtil.*;
import data.common.*;
import data.request.parameters.ResetMyPasswordParameter;
import data.request.parameters.SetUserHeadPhotoParameter;
import data.request.parameters.SignInParameter;
import data.response.results.CommonInfo;
import init.config.RegisterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.CommonService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.time.format.DateTimeFormatter;

/**
 * Created by liudongdong on 2015/4/27.
 */
//即使不提供路径，也要加个path属性
@Path("/")
public class CommonResources {
    private static final Log logger = LogFactory.getLog(CommonResources.class);
    private CommonService commonService = new CommonService();

    @Path("/ApiVersion")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> apiVersion() {
        return ResultWrapper.<String>success(CommonVariable.getApiVersion());
    }

    @Path("/GetExpireTime")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> getExpireTime() {
        String result;
        if (RegisterService.getExpireTime() == null) {
            result = "没有获取到授权过期时间";
        } else {
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            result =dtf.format(RegisterService.getExpireTime());
        }
        return ResultWrapper.<String>success(result);
    }

    @Path("/GetProjToken")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> getProjToken() {
        return ResultWrapper.<String>success(CommonVariable.getProjToken());
    }

    @Path("/GetCommonInfo")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<CommonInfo> getCommonInfo(@Context HttpHeaders httpHeaders) {
        AccessType accessType = AccessType.valueOf(httpHeaders.getHeaderString("access_type").toUpperCase());
        CommonInfo ci = new CommonInfo();
        ci.setApiVersion(CommonVariable.getApiVersion());
        ci.setProjToken(CommonVariable.getProjToken());
        ci.setAppName(AppConfig.getAppName(accessType));
        ci.setBackground(AppConfig.getBG(accessType));
        return ResultWrapper.<CommonInfo>success(ci);
    }

    @Path("/TestLog")
    @GET
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> testLog() {
        try {
            throw new RuntimeException("testec");
        } catch (Exception ex) {
            logger.error(ex);
        }
        return ResultWrapper.success("test log finished");
    }

    @POST
    @Path("/SignIn")
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> signIn(@NotNull @Valid SignInParameter signInParameter, @Context final HttpHeaders headers) {
        try {
            AccessType accessType = AccessType.valueOf(headers.getHeaderString(CommonVariable.ACCESS_TYPE_HEADER_NAME).toUpperCase());
            String accessToken = commonService.signIn(signInParameter.getAccount(), signInParameter.getPassword(), accessType);
            if (!StringUtil.isNullOrEmpty(accessToken)) {
                //日志处理
                TbUser user = AccessTokenPool.getInstance().getUserInformation(accessToken).getUser();
                String logMsg = user.getName() == null ? user.getAccount() : user.getName();
                LogUtil.writeLog(user.getId(), null, LogType.User,
                        "用户<" + logMsg + ">登入了系统");
                return ResultWrapper.success(accessToken);
            }
        } catch (Exception ex) {
            String message = ex.getMessage();
            if (!StringUtil.isNullOrEmpty(message)
                    && message.equals("当前用户不允许此访问类型"))
                return ResultWrapper.fail(ErrCode.ERR_ACCESS_TYPE);
            logger.error("登录发生错误:|" + signInParameter.getAccount() + "--" + signInParameter.getPassword() + "|", ex);
            return ResultWrapper.serverException(ex.getMessage());
        }
        return ResultWrapper.fail(ErrCode.ERR_USERNAME);
    }

    @GET
    @Path("/SignOut")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> signOut(@Context final HttpHeaders headers) {
        ResultWrapper<String> result = null;
        try {
            String accessToken = headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            //日志处理
            TbUser user = AccessTokenPool.getInstance().getUserInformation(accessToken).getUser();
            commonService.signOut(accessToken);
            String logMsg = user.getName() == null ? user.getAccount() : user.getName();
            LogUtil.writeLog(user.getId(), null, LogType.User,
                    "用户<" + logMsg + ">退出了系统");
            result = ResultWrapper.<String>success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error("登出发生错误", ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @GET
    @Path("/GetMyInfo")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<TbUser> getMyInfo(@Context final HttpHeaders headers) {
        ResultWrapper resultWrapper = null;
        try {
            String accessToken = headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            TbUser user = commonService.getMyInfo(accessToken).withFullPathUser();
            resultWrapper = ResultWrapper.success(user);
        } catch (Exception ex) {
            logger.error("获取我的信息出错", ex);
            resultWrapper = ResultWrapper.serverException(ex.getMessage());
        }
        return resultWrapper;
    }

    @POST
    @Path("/SetUserHeadPhoto")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> setUserHeadPhoto(SetUserHeadPhotoParameter pa, @Context final HttpHeaders headers) {
        ResultWrapper<String> resultWrapper = null;
        try {
            String accessToken = headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            String path = commonService.setUserHeadPhoto(pa, accessToken);
            return ResultWrapper.success(PathUtil.getPath(path));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            resultWrapper = ResultWrapper.serverException(ex.getMessage());
        }
        return resultWrapper;
    }

    @POST
    @Path("/GetMyPower")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<TbProjPower> getMyPower(int projID, @Context final HttpHeaders headers) {
        ResultWrapper resultWrapper = null;
        try {
            String accessToken = headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            TbProjPower power = commonService.getMyPower(projID, accessToken);
            resultWrapper = ResultWrapper.success(power);
        } catch (Exception ex) {
            logger.error("获取我的权限信息出错", ex);
            resultWrapper = ResultWrapper.serverException(ex.getMessage());
        }
        return resultWrapper;
    }

    @POST
    @Path("/UpdateMyInfo")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateMyInfo(@Context HttpHeaders headers, @NotNull @Valid TbUser user) {
        ResultWrapper result = null;
        try {
            String accessToken = headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            commonService.updateMyInfo(user, accessToken);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error("修改我的信息发生错误", ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/ResetMyPassword")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Boolean> resetMyPassword(@Context final HttpHeaders headers, @NotNull @Valid ResetMyPasswordParameter pa) {
        ResultWrapper result = null;
        try {
            String accessToken = headers.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
            result = ResultWrapper.success(commonService.resetMyPassword(pa, accessToken));
        } catch (Exception ex) {
            logger.error("修改我的信息发生错误", ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

}























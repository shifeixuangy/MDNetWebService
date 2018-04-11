package medoresources;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbUser;
import data.request.parameters.AddUserInfoParameter;
import data.request.parameters.ResetUserPasswordParameter;
import data.response.results.GetProjUserListResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.AdminService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/4/28.
 */
@Path("/")
public class AdminResources {
    private  static final Log logger= LogFactory.getLog(AdminResources.class);
    private AdminService adService=new AdminService();
    @GET
    @Path("/GetUserList")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbUser>>getUserList()
    {
        ResultWrapper result=null;
        try {
            List<TbUser>userList=adService.getUserList();
            if(!CollectionUtil.isNullOrEmpty(userList))
                userList=userList.stream().map(TbUser::withFullPathUser).collect(Collectors.toList());
            result= ResultWrapper.success(userList);
        }
        catch (Exception ex)
        {
            logger.error("获取用户列表错误",ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/GetProjUserList")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<GetProjUserListResult>>getProjUserList(int projID)
    {
        ResultWrapper<List<GetProjUserListResult>> result=null;
        try {
            result=ResultWrapper.success(adService.getProjUserList(projID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/AddUserInfo")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addUserInfo(@NotNull @Valid AddUserInfoParameter pa)
    {
        ResultWrapper result=null;
        try {
            result=ResultWrapper.success(adService.addUserInfo(pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @POST
    @Path("/UpdateUserInfo")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateUserInfo(@NotNull @Valid  TbUser user)
    {
        ResultWrapper result=null;
        try
        {
            adService.updateUserInfo(user);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @POST
    @Path("/ResetUserPassword")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>resetUserPassword(ResetUserPasswordParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            adService.resetUserPassword(pa.getUID(),pa.getNewPassword());
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }
    @POST
    @Path("/DeleteUser")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteUser(int uid)
    {
        ResultWrapper<String>result=null;
        try
        {
            adService.deleteUser(uid);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

}










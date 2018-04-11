package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.proj.TbGroup;
import data.request.parameters.AddGroupParameter;
import data.request.parameters.DeleteGroupParameter;
import data.request.parameters.UpdateGroupParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class GroupResources {
    private static final Log logger= LogFactory.getLog(GroupResources.class);
    private GroupService gService=new GroupService();
    @Path("/GetGroupList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbGroup>>getGroupList(int projID)
    {
        ResultWrapper<List<TbGroup>>result=null;
        try
        {
            result=ResultWrapper.success(gService.getGroupList(projID));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/AddGroup")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer>addGroup(@NotNull @Valid AddGroupParameter pa)
    {
        ResultWrapper<Integer>result=null;
        try
        {
            result=ResultWrapper.success(gService.addGroup( pa));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/UpdateGroup")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>updateGroup(@NotNull @Valid UpdateGroupParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            gService.updateGroup(pa);
            result=ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            result=ResultWrapper.serverException(ex.getMessage());
        }
        return  result;
    }
    @Path("/DeleteGroup")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String>deleteGroup(@NotNull @Valid DeleteGroupParameter pa)
    {
        ResultWrapper<String>result=null;
        try
        {
            gService.deleteGroup(pa);
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














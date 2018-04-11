package filters;

import commonUtil.AccessPermissions;
import commonUtil.AccessTokenPool;
import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import data.common.AccessType;
import data.common.Permission;
import data.common.UserInformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by liudongdong on 2015/12/30.
 */
@Provider
@PreMatching
//该过滤器调用在验证用户之后
@Priority(Priorities.AUTHENTICATION+1)
public class AccessTypeFilter implements ContainerRequestFilter {
    private static Log logger= LogFactory.getLog(AccessTypeFilter.class);
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path=requestContext.getUriInfo().getPath();
        if(AccessPermissions.getLevelPermissions(Permission.PUBLIC).contains(path))
        {
            if(!path.toLowerCase().equals("signin"))
                return;
        }
        String accessType=requestContext.getHeaderString(CommonVariable.ACCESS_TYPE_HEADER_NAME);
        if(StringUtil.isNullOrEmpty(accessType))
        {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
            return;
        }
        AccessType sType=null;
        try
        {
            sType=AccessType.valueOf(accessType.toUpperCase());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
            return;
        }
        if(sType==null)
        {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
            return;
        }
        if(path.toLowerCase().equals("signin"))
            return;
        String accessToken=requestContext.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
        UserInformation userInformation= AccessTokenPool.getInstance().getUserInformation(accessToken);
        if(AccessType.allowAccess(sType,userInformation.getUser()))
            return;
        //拒绝访问
        else
        {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            return;
        }
    }
}

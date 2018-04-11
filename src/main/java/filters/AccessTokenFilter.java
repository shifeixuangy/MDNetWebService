package filters;

import commonUtil.*;
import data.common.Permission;
import data.common.TbUser;
import data.common.UserInformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by liudongdong on 2015/4/23.
 */
@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class AccessTokenFilter implements ContainerRequestFilter {
    private static final Log logger= LogFactory.getLog(AccessTokenFilter.class);
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(!CommonVariable.isAuthenticateEnabled())
            return;
        String path=requestContext.getUriInfo().getPath();
        if(!requestContext.getHeaders().containsKey(CommonVariable.ACCESS_TOKEN_HEADER_NAME))
        {
            //没有包含访问标记，只能访问公共api
            if(AccessPermissions.getLevelPermissions(Permission.PUBLIC).contains(path))
                return;
            //没有包含访问标记，且不是公共api
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            return;
        }

        //包含访问标记，判断相应的访问标记的权限
        String accessToken=requestContext.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
        if(StringUtil.isNullOrEmpty(accessToken))
        {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            return;
        }
        UserInformation userInfo= AccessTokenPool.getInstance().getTokenUserInfoAndReset(accessToken, LocalDateTime.now());
        if(userInfo==null)
        {    //内存中没有访问标记的用户信息，标记已过期
             requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        //有访问标记
        int projID=CommonVariable.DEFAULT_PROJID;
        if(ProjIDExtract.hasProjIDProperty(path))
        {
            projID=ProjIDExtract.extractRequestProjID(requestContext,true);
        }
        requestContext.getHeaders().add("ProjID",Integer.toString(projID));
        if(!(AccessPermissions.getLevelPermissions(userInfo.getAccessLevel(projID)).contains(path)))
        {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            return;
        }


    }
}

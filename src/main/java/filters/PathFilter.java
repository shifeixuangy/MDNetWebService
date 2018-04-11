package filters;

import commonUtil.StringUtil;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by liudongdong on 2015/4/24.
 */
@Provider
@PreMatching
//该过滤器调用在验证用户之前
@Priority(Priorities.AUTHENTICATION-2)
public class PathFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path=requestContext.getUriInfo().getPath();
        if(StringUtil.isNullOrEmpty(path))
        {
            requestContext.abortWith(Response.notAcceptable(null).build());
        }
    }
}

package filters;

import commonUtil.CommonVariable;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Administrator on 2016/4/7.
 */
@Provider
@PreMatching
//该过滤器调用在验证用户之前
@Priority(Priorities.AUTHENTICATION-5)
public class RegisterFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(CommonVariable.getIsValid())
            return;
        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).header("Content-Type","text/plain;charset=UTF-8").entity("您的注册码验证失败，请您获取正版授权").build());
    }
}

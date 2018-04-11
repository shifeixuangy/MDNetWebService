package filters;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by admin on 2016/5/10.
 */
@Provider
@PreMatching
//该过滤器调用在验证用户之前
@Priority(Priorities.AUTHENTICATION-3)
public class CrossOriginOptionsFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(!requestContext.getMethod().toLowerCase().equals("options"))
            return;
//        headers.add("Access-Control-Allow-Origin","*");
//        headers.add("Access-Control-Max-Age",86400);
//        headers.add("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE,PUT");
//        headers.add("Access-Control-Allow-Headers","Content-Type,access_token,access_type,content-type");
//        String origin=null;
//        MultivaluedMap<String,String>headers=requestContext.getHeaders();
//        if(headers.containsKey("origin"))
//            origin=headers.getFirst("origin");
//        if(headers.containsKey("Origin"))
//            origin=headers.getFirst("Origin");

        Response response= Response.ok()
////                .header("Access-Control-Allow-Origin",origin==null?"*":origin)
//                .header("Access-Control-Max-Age",86400)
//                .header("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE,PUT")
//                .header("Access-Control-Allow-Headers","Content-Type,access_token,access_type,content-type")
                .build();
        requestContext.abortWith(response);
    }
}

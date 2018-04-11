package filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by liudongdong on 2015/6/6.
 */
@Provider
public class OriginResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String,Object>headers=responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Max-Age",86400);
        headers.add("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE,PUT");
        headers.add("Access-Control-Allow-Headers","Content-Type,access_token,access_type,content-type");
    }
}

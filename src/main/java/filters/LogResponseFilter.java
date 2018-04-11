package filters;

import commonUtil.*;
import data.common.TbLog;
import data.common.UserInformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

//用户登录没有Access_token所以要特殊处理
//登出，内存中没有用户信息了所以要特殊处理
@Provider
public class LogResponseFilter implements ContainerResponseFilter {
    private static final Log logger= LogFactory.getLog(LogResponseFilter.class);
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String path=requestContext.getUriInfo().getPath();
        String accessToken = requestContext.getHeaderString(CommonVariable.ACCESS_TOKEN_HEADER_NAME);
        if (StringUtil.isNullOrEmpty(accessToken))
            return;
        try
        {
            UserInformation userInfo=AccessTokenPool.getInstance().getUserInformation(accessToken);
            if(userInfo==null)
                return;
            String userName=userInfo.getUser().getName();
            if(StringUtil.isNullOrEmpty(userName))
                userName=userInfo.getUser().getAccount();
            userName="用户<"+userName+">";
            TbLog log= LogMessages.getLog(path,userName);
            if(log==null)
                return;
            if(ProjIDExtract.hasProjIDProperty(path))
            {
                String projIDString=requestContext.getHeaderString("ProjID");
                if(!StringUtil.isNullOrEmpty(projIDString))
                {
                    int projID=Integer.parseInt(projIDString);
                    log.setProjId(projID);
                }
            }
            log.setUid(userInfo.getUid());
            LogUtil.writeLog(log);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }
}

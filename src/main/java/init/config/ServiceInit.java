package init.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * Created by liudongdong on 2015/12/9.
 */
public class ServiceInit extends HttpServlet {
    private static Log logger= LogFactory.getLog(ServiceInit.class);
    @Override
    public void init() throws ServletException {
        super.init();
        //授权验证服务
        try
        {
            logger.info("正在开启授权验证");
            RegisterService.init();
            logger.info("授权验证完成");
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
        //统计平均数据服务
        try
        {
            logger.info("正在开启统计服务");
            CaculateService.init();
            logger.info("统计服务开启成功");
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
        //清理日志服务
        try
        {
            logger.info("正在开启日志清除服务");
            LogCleanerInit.init();
            logger.info("日志清除服务开启成功");
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
        //DB自动升级
        try
        {
            logger.info("正在执行DB自动升级任务");
            DBUpdateService.start();
            logger.info("DB自动升级任务执行完毕");
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}

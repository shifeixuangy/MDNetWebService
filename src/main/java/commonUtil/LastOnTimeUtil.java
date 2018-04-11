package commonUtil;

import data.common.TbUser;
import data.common.UserInformation;
import dataDAO.DataHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liudongdong on 2015/8/17.
 */
public class LastOnTimeUtil {
    private static final Log logger = LogFactory.getLog(LastOnTimeUtil.class);
    private static ExecutorService es;

    static {
        es = Executors.newFixedThreadPool(1);
    }

    public static void WriteUserLastOnTime(UserInformation userInfo) {
        try {
            TbUser user = userInfo.getUser();
            if (user == null)
                return;
            user.setLastOnLine(Timestamp.valueOf(userInfo.getTokenTime()));
            es.execute(() -> {
                try {
                    DataHelper.update(user, CommonVariable.DEFAULT_PROJID);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
            });
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}

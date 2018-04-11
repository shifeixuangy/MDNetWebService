package init.config;

import data.common.WarnLogMsg;

/**
 * Created by liudongdong on 2015/10/4.
 */
public interface MessageHandler {
    void onMessageReceiver(WarnLogMsg msg);
}

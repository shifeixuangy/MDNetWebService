package init.config;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import data.common.WarnLogMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by liudongdong on 2015/10/4.
 */
public class PushMessageHandler implements MessageHandler {
    private static Log logger = LogFactory.getLog(PushMessageHandler.class);

    private String apiKey;
    private String secretKey;

    public PushMessageHandler(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public static PushMessageHandler getInstance() {
        return new PushMessageHandler(CommonVariable.getApiKey(), CommonVariable.getSecretKey());
    }

    @Override
    public void onMessageReceiver(WarnLogMsg msg) {
        if(StringUtil.isNullOrEmpty(msg.getProjToken()))
            msg.setProjToken(CommonVariable.getProjToken());
        // 1. get apiKey and secretKey from developer console
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
//                logger.info(event.getMessage());
            }
        });
        String msgString = null;
        try {
            msgString = CommonVariable.getObjectMapper().writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        try {
            // 4. specify request arguments
            String tagName=TagInit.getProjTagName(msg.getProjID());
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName(tagName)
//                    .addMsgExpires(new Integer(3600))
                    .addMessageType(0)
                    .addMessage(msgString) //添加透传消息
//                    .addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
                    .addDeviceType(3);
            // 5. http request
            PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
            // Http请求结果解析打印
            if (CommonVariable.isPushDebugEnable())
                logger.info("发送消息：" + msgString);
        } catch (PushServerException ex) {
            logger.error("未传输的警报日志：" + msgString);
            logger.error(ex.getErrorCode() + ex.getErrorMsg(), ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}

package init.config;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.model.*;
import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.TbProjInfo;
import dataDAO.CommonQueryHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/10/7.
 */
public class TagInit {
    private String apiKey;
    private String secretKey;

    public static void init()
    {
        try {
            List<TbProjInfo> pis = CommonQueryHelper.getProjInfo();
            if(CollectionUtil.isNullOrEmpty(pis))
                return;
            TagInit ti=getDefaultTagInit();
            List<String>tags=ti.getTags();
            for(TbProjInfo pi:pis)
            {
                String tagName=getProjTagName(pi.getId());
                if(tags.contains(tagName))
                    continue;
                ti.addTag(tagName);
            }
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
        if(CommonVariable.isPushDebugEnable())
        {
            try
            {
                TagDebugService.getInstance().start();
                logger.info("测试消息推送开启成功");
            }
            catch (Exception ex)
            {
                logger.error(ex.getMessage(),ex);
            }
        }
    }

    public static String getProjTagName(int projID)
    {
        return CommonVariable.getProjToken()+Integer.toString(projID);
    }

    public TagInit(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    private static final Log logger= LogFactory.getLog(TagInit.class);
    public List<String> getTags() {
        List<String>result=new LinkedList<>();
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
                logger.info(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryTagsRequest request = new QueryTagsRequest()
//                    .addTagName("xxxxx").addStart(0).addLimit(10)
                    .addDeviceType(3);
            // 5. http request
            QueryTagsResponse response = pushClient.queryTags(request);
            if (null != response) {
                List<?> list = response.getTagsInfo();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if (object instanceof TagInfo) {
                        TagInfo tagInfo = (TagInfo) object;
                        result.add(tagInfo.getTagName());
                    }
                }
            }
        } catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
        return result;
    }

    public void addTag(String tagName)
    {
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
                logger.info(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            CreateTagRequest request = new CreateTagRequest().addTagName(
                    tagName).addDeviceType(3);
            // 5. http request
            CreateTagResponse response = pushClient.createTag(request);
            logger.info(String.format("tagName: %s, result: %d",
                    response.getTagName(), response.getResult()));
        } catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }

    public void deleteTag(String tagName)
    {
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
                logger.info(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            DeleteTagRequest request = new DeleteTagRequest().addTagName(
                    tagName).addDeviceType(new Integer(3));
            // 5. http request
            DeleteTagResponse response = pushClient.deleteTag(request);
            // Http请求结果解析打印
            logger.info(String.format("tagName: %s, result: %d",
                    response.getTagName(), response.getResult()));
        }catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }

    public static TagInit getDefaultTagInit()
    {
        return  new TagInit(CommonVariable.getApiKey(),CommonVariable.getSecretKey());
    }

}

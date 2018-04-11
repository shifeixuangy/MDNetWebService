package commonUtil;

import data.common.Permission;
import data.common.UserInformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liudongdong on 2015/4/11.
 */
public class AccessTokenPool {
    private static Log logger = LogFactory.getLog(AccessTokenPool.class);
    private static final AccessTokenPool pool = new AccessTokenPool();

    public static AccessTokenPool getInstance() {
        return pool;
    }


    private ConcurrentMap<String, UserInformation> loginUsers = new ConcurrentHashMap<>();

    private AccessTokenPool() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                this.clearExpireToken();
                if (!CommonVariable.IS_DEBUG_ENABLE)
                    return;
                StringBuilder builder = new StringBuilder();
                this.loginUsers.forEach((token, ui) -> {
                    builder.append(ui.getUser().getAccount() + ",");
                });
                logger.info("目前内存中的访问标记数量：" + this.getLoginUserCount() + builder.toString());

            } catch (Exception ex) {
                logger.error("清理过期的访问标记发生错误", ex);
            }
        }, CommonVariable.getKeyCheckInterval(), CommonVariable.getKeyCheckInterval(), TimeUnit.SECONDS);
    }


    public String addToken(UserInformation user) {
        //判断当前用户如果已登录，则清除
        final HoldString hs = new HoldString(null);
        loginUsers.forEach((sToken, userInfo) -> {
            if (userInfo.getUser().getAccount().equals(user.getUser().getAccount()))
                if (userInfo.getAccessType().equals(user.getAccessType()))
                    hs.setsValue(sToken);
        });
        if (!StringUtil.isNullOrEmpty(hs.getsValue()))
            loginUsers.remove(hs.getsValue());
        String accessToken = AccessTokenGenerate.getAccessToken();
        loginUsers.put(accessToken, user);
        return accessToken;
    }


    public boolean containsAccessToken(String accessToken) {
        return loginUsers.containsKey(accessToken);
    }

    public UserInformation getTokenUserInfoAndReset(String accessToken, LocalDateTime time) {
        try {
            UserInformation user = loginUsers.get(accessToken);
            if (user == null)
                return null;
            user.setTokenTime(time);
            //如果在get之后，设置tokenTime之前，该token被清除，则重新添加
            loginUsers.putIfAbsent(accessToken, user);
            return user;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /***
     * @param token 用户的访问标记
     * @return 获取当前已经登录的用户，如果不存在或者已超时，返回NULL
     */
    public UserInformation getUserInformation(String token) {
        return loginUsers.get(token);
    }

    public int getLoginUserCount() {
        try {
            return loginUsers.size();
        } catch (Exception ex) {
            throw ex;
        }
    }


    public void clearExpireToken() {
        LocalDateTime dtBegin = LocalDateTime.now().minusMinutes(CommonVariable.getKeyExpireInterval());
        List<String> removeKeys = new LinkedList<String>();
        loginUsers.forEach((s, u) -> {
            if (u.getTokenTime().isBefore(dtBegin))
                removeKeys.add(s);
        });
        if (removeKeys.size() > 0) {
            try {
                removeKeys.forEach(s -> {
                    setUserLastOnTime(s);
                    loginUsers.remove(s);
                });
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public void clearAllLoginUsers() {
        try {
            loginUsers.clear();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteAccessToken(String accessToken) {
        setUserLastOnTime(accessToken);
        loginUsers.remove(accessToken);
    }

    private void setUserLastOnTime(String accessToken) {
        UserInformation userInfo = getUserInformation(accessToken);
        if (userInfo == null)
            return;
        LastOnTimeUtil.WriteUserLastOnTime(userInfo);
    }


    public static class HoldString {
        private String sValue;

        public HoldString(String hs) {
            this.sValue = hs;
        }

        public String getsValue() {
            return sValue;
        }

        public void setsValue(String sValue) {
            this.sValue = sValue;
        }
    }


}

package init.config;

import commonUtil.*;
import dataDAO.register.RegisterOperator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/4/7.
 */
public class RegisterService {
    private static Log logger= LogFactory.getLog(RegisterService.class);
    private static final String REGISTER_MAC = "netmac";
    private static final String REGISTER_CODE = "netcode";
    private static LocalDateTime expireTime;
    //24*60*60*1000
    private static final long PERIOD=24*60*60*1000;

    public static void init()
    {
        Timer timer=new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    logger.info("开始验证权限");
                    RegisterService rs=new RegisterService();
                    rs.start();
                    logger.info("验证完毕,授权状态："+(CommonVariable.getIsValid()?"授权":"非经授权"));
                }
                catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                }
            }
        },0,PERIOD);
    }

    public void start() {
        RegisterOperator ro = new RegisterOperator();
        String registerMac = ro.readValue(REGISTER_MAC);
        if(registerMac!=null)
            registerMac=registerMac.toLowerCase();
        String registerCode = ro.readValue(REGISTER_CODE);
        String register = CommonVariable.getRegister();
        //尚未在注册表中写入mac和code
        if (StringUtil.isNullOrEmpty(registerMac) || StringUtil.isNullOrEmpty(registerCode)) {
            if (StringUtil.isNullOrEmpty(register)) {
                CommonVariable.setIsValid(false);
                return;
            }
            RegisterCodeResolver configResolver=new RegisterCodeResolver(register);
            logger.info("验证信息："+configResolver.getMac()+"--"+configResolver.getOutTime().toString());
            RegisterInfo ri = new RegisterInfo(configResolver.getOutTime(),configResolver.getMac());
            if (!ri.isValid()) {
                CommonVariable.setIsValid(false);
                return;
            }
            logger.info("开始写入注册表");
            ro.writeValue(REGISTER_MAC, configResolver.getMac());
            ro.writeValue(REGISTER_CODE, register.toLowerCase());
            logger.info("注册表写入完成");
            CommonVariable.setIsValid(true);
            return;
        }
        //注册表中有信息
        else {
            try {
                if(!register.equalsIgnoreCase(registerCode))
                {
                    ro.deleteValue(REGISTER_MAC);
                    ro.deleteValue(REGISTER_CODE);
                    new RegisterService().start();
                    return;
                }
                RegisterCodeResolver registerResolver=new RegisterCodeResolver(registerCode);
                RegisterInfo ri = new RegisterInfo(registerResolver.getOutTime(), registerResolver.getMac());
                if (!ri.isValid(registerMac)) {
                    CommonVariable.setIsValid(false);
                    return;
                } else {
                    CommonVariable.setIsValid(true);
                    return;
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static LocalDateTime getExpireTime() {
        return expireTime;
    }

    class RegisterInfo {
        private LocalDateTime outTime;
        private String mac;

        /**
         * 通过注册码中的过期时间和MAC地址来判断授权是否有效
         * @param outTime
         * @param mac
         */
        RegisterInfo(LocalDateTime outTime, String mac) {
            this.outTime = outTime;
            this.mac = mac;
            expireTime=outTime;
        }

        /**
         * 验证本地MAC地址是否包含注册码中的MAC地址
         * @return 授权是否有效
         */
        public boolean isValid() {
            if (outTime.isBefore(LocalDateTime.now())) {
                logger.info("授权时间过期"+outTime.toString());
                return false;
            }
            List<String> allMacs = HardwareUtil.getAllLocalMacs();
            if (!allMacs.contains(mac))
            {
                logger.info("mac地址验证失败"+allMacs.stream().reduce((s1,s2)->s1+","+s2)+"单个的mac地址为："+mac);
                return false;
            }
            return true;
        }

        /**
         * 验证注册码中的mac地址是否等于注册表中的mac地址
         * @param mac
         * @return 授权是否有效
         */
        public boolean isValid(String mac) {
            if (outTime.isBefore(LocalDateTime.now()))
                return false;
            if (!this.mac.equals(mac))
                return false;
            return true;
        }
    }

    public static class RegisterCodeResolver
    {
        private String registerCode;
        private String mac;
        private LocalDateTime outTime;

        public RegisterCodeResolver(String registerCode) {
            if(StringUtil.isNullOrEmpty(registerCode))
                throw new IllegalArgumentException("注册码为空");
            this.registerCode = registerCode.toUpperCase();
            try {
                String message = DesUtil.decrypt(this.registerCode);
                outTime= TimeUtil.valueOf( message.substring(0, 13));
                mac= message.substring(13).toLowerCase();
            }
            catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }
        }

        public String getMac()
        {
            return mac;
        }

        public LocalDateTime getOutTime()
        {
            return  outTime;
        }
    }

}

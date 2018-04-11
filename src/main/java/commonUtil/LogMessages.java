package commonUtil;

import data.common.LogType;
import data.common.TbLog;

/**
 * Created by liudongdong on 2015/8/5.
 */
public class LogMessages {
    public static TbLog getLog(String path,String msg)
    {
        switch (path)
        {
            case "UpdateMyInfo":return getLog(LogType.User,msg+"修改了自己的用户信息");
            case "ResetMyPassword":return getLog(LogType.User,msg+"修改了登录密码");
            case "UpdateProjInfo":return getLog(LogType.System,msg+"修改了项目信息");
            case "UpdateRegion":return getLog(LogType.System,msg+"修改了区域信息");
            case "AddGroup":return getLog(LogType.System,msg+"添加了传感器分组");
            case "UpdateGroup":return getLog(LogType.System,msg+"修改了传感器分组");
            case "DeleteGroup":return getLog(LogType.System,msg+"删除了传感器分组");
            case "AddSensor":return getLog(LogType.System,msg+"添加了传感器");
            case "UpdateSensor":return getLog(LogType.System,msg+"修改了传感器");
            case "DeleteSensor":return getLog(LogType.System,msg+"删除了传感器");
            case "AddMonitorArea":return getLog(LogType.System,msg+"添加了监视区域");
            case "UpdateMonitorArea":return getLog(LogType.System,msg+"修改了监视区域");
            case "DeleteMonitorArea":return getLog(LogType.System,msg+"删除了监视区域");
            case "AddWarnPerson":return getLog(LogType.System,msg+"添加了警报联系人");
            case "UpdateWarnPerson":return getLog(LogType.System,msg+"修改了警报联系人");
            case "DeleteWarnPerson":return getLog(LogType.System,msg+"删除了警报联系人");
            case "AddWarnValue":return getLog(LogType.System,msg+"添加了警报阈值");
            case "UpdateWarnValue":return getLog(LogType.System,msg+"修改了警报阈值");
            case "DeleteWarnValue":return getLog(LogType.System,msg+"删除了警报阈值");
            case "AddWarnRevise":return getLog(LogType.System,msg+"添加了修正值");
            case "UpdateWarnRevise":return getLog(LogType.System,msg+"修改了修正值");
            case "DeleteWarnRevise":return getLog(LogType.System,msg+"删除了修正值");
//            case "DealWarnLog":return getLog(LogType.System,msg+"处理了警报");
//            case "BatchDealWarnLog":return getLog(LogType.DealWarn,msg+"批量处理了警报");
            case "AddUserInfo":return getLog(LogType.System,msg+"添加了用户信息");
            case "UpdateUserInfo":return getLog(LogType.System,msg+"修改了用户信息");
            case "ResetUserPassword":return getLog(LogType.System,msg+"重置了用户密码");
            case "DeleteUser":return getLog(LogType.System,msg+"删除了用户信息");
            case "AddProjInfo":return getLog(LogType.System,msg+"添加了项目信息");
            case "DeleteProjInfo":return getLog(LogType.System,msg+"删除了项目信息");
            case "AddRegion":return getLog(LogType.System,msg+"添加了区域信息");
            case "DeleteRegion":return getLog(LogType.System,msg+"删除了区域信息");
            case "AddProjMgr":return getLog(LogType.System,msg+"添加了项目权限");
            case "UpdateProjMgr":return getLog(LogType.System,msg+"修改了项目权限");
            case "DeleteProjMgr":return getLog(LogType.System,msg+"删除了项目权限");
            case "AddRegionMgr":return getLog(LogType.System,msg+"添加了区域关系");
            case "UpdateRegionMgr":return getLog(LogType.System,msg+"修改了区域关系");
            case "DeleteRegionMgr":return getLog(LogType.System,msg+"删除了区域关系");
            default:return null;
        }
    }

    private static TbLog getLog(LogType logType,String logMsg)
    {
        TbLog log=new TbLog();
        log.setLogMsg(logMsg);
        log.setLogType(logType.toShort());
        return  log;
    }
}

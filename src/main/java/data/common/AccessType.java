package data.common;

/**
 * Created by liudongdong on 2015/12/29.
 */
public enum AccessType {
    WEB("web"),ANDROID("android"),DESKTOP("desktop"),IOS("ios");
    private String accessType;
    AccessType(String accessType)
    {
        this.accessType=accessType;
    }

    public int toInt()
    {
        switch (accessType)
        {
            case "android":
                return 1;
            case "web":
                return 2;
            case "desktop":
                return 4;
            case "ios":
                return 8;
            default:return 0;
        }
    }

    public static boolean allowAccess(AccessType sType,TbUser user)
    {
        if(user.isAdmin())
            return true;
        if(user.getAllowAccessType()==0)
            return true;
        int result=(user.getAllowAccessType())&(sType.toInt());
        if(result>0)
            return true;
        return false;
    }
}

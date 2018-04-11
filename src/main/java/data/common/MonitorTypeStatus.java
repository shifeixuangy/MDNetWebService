package data.common;

/**
 * Created by liudongdong on 2016/3/8.
 */
public enum MonitorTypeStatus {
    ALL(0),VALID(1),UNVALID(2);
    private int code;
    MonitorTypeStatus(int code)
    {
        this.code=code;
    }

    public Boolean isValid()
    {
        switch (this)
        {
            case ALL:
                return null;
            case VALID:
                return true;
            case UNVALID:
                return false;
            default:return true;
        }
    }

    public static  MonitorTypeStatus valueOf(int code)
    {
        switch (code)
        {
            case 0:
                return ALL;
            case 1:
                return VALID;
            case 2:
                return UNVALID;
            default:return VALID;
        }
    }
}

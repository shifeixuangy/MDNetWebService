package data.common;

/**
 * Created by liudongdong on 2015/8/5.
 */
public enum LogType {
    Normal((short)1),
    User((short)2),
    System((short)3),
    Communication((short)4),
    DealWarn((short)5),
    All((short)10)
    ;
    private short logCode;
    LogType(short code)
    {
        this.logCode=code;
    }

    public short toShort()
    {
        return this.logCode;
    }

    public static LogType valueOf(short code)
    {
        switch (code)
        {
            case 1: return LogType.Normal;
            case 2:return LogType.User;
            case 3:return LogType.System;
            case 4:return LogType.Communication;
            case 5:return LogType.DealWarn;
            case 10:return LogType.All;
            default:return  LogType.All;
        }
    }
}

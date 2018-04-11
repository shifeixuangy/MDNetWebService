package data.common;

/**
 * Created by liudongdong on 2015/4/29.
 */
public enum SensorType {
    NO_SUCH_TYPE(-2),
    All(-1),

    /**
     * 地表位移
     */
    TYPE_GPS(0),
    /**
     * 全站仪
     */
    TYPE_TPS(1),
    /**
     * 内部位移
     */
    TYPE_DPM(2),
    /**
     * 雨量
     */
    TYPE_RAIN(3),
    /**
     * 水位计
     */
    TYPE_WATER(4),
    /**
     * 干滩
     */
    TYPE_DRY_BEACH(5),
    /**
     * 温度计
     */
    TYPE_TEMPERATURE(6),
    /**
     * 浸润线
     */
    TYPE_SATURATURE(7),
    /**
     * 倾角仪
     */
    TYPE_DIP_ANGLE(8),
    /**
     * 静力水准仪
     */
    TYPE_HYDR_LEVEL(9),
    /**
     * 视频                           无
     */
    TYPE_VIDEO(10),
    /**
     * 量水堰
     */
    TYPE_FL(11),
    /**
     * 浊度仪                        跳了一个数字
     */
    TYPE_TURBIDITY(13),
    /**
     * 裂缝计
     */
    TYPE_DS(14),
    /**
     * 土壤含水率
     */
    TYPE_SMC(15),
    /**
     * 爆破震动传感器                    无
     */
    TYPE_SHOCK(16),
    /**
     * 多点位移计
     */
    TYPE_DS2(17),
    /**
     * 新全站仪
     */
    TYPE_TPS2(18)
    ;

    private int sensorCode;
    private SensorType(int sCode)
    {
        this.sensorCode=sCode;
    }

    public int toInt()
    {
        return  sensorCode;
    }

    public static SensorType valueOf(int sensorType)
    {
        switch (sensorType)
        {
            case -1:
                return All;
            case 0:
                return TYPE_GPS;
            case 1:
                return TYPE_TPS;
            case 2:
                return TYPE_DPM;
            case 3:
                return TYPE_RAIN;
            case 4:
                return TYPE_WATER;
            case 5:
                return TYPE_DRY_BEACH;
            case 6:
                return TYPE_TEMPERATURE;
            case 7:
                return TYPE_SATURATURE;
            case 8:
                return TYPE_DIP_ANGLE;
            case 9:
                return TYPE_HYDR_LEVEL;
            case 10:
                return TYPE_VIDEO;
            case 11:
                return TYPE_FL;
            case 13:
                return TYPE_TURBIDITY;
            case 14:
                return TYPE_DS;
            case 15:
                return TYPE_SMC;
            case 16:
                return TYPE_SHOCK;
            case 17:
                return TYPE_DS2;
            case 18:
                return TYPE_TPS2;
            default:return NO_SUCH_TYPE;
        }



    }

























}











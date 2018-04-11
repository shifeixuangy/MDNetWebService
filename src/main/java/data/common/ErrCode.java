package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liudongdong on 2015/4/23.
 */

public class ErrCode {

    public static final int NONE=0;
    public static final int ERR_USERNAME=1;
    public static final int ERR_PASSWORD=2;
    public static final int ERR_POWER=3;
    public static final int ERR_API=4;
    public static final int ERR_VERSION=5;
    public static final int ERR_NO_SENSORTYPE=6;
    public static final int ERR_NO_SENSOR=7;
    public static final int ERR_SERVER_INTERNAL_ERROR=8;
    public static final int ERR_ACCESS_TYPE=9;
}

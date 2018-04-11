package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/4/23.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ErrInformation {
    private  int code;
    private String errMessage;

    public ErrInformation()
    {}

    public ErrInformation(int code,String info)
    {
        this.code=code;
        this.errMessage=info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public static ErrInformation success()
    {
       return  new ErrInformation(ErrCode.NONE,null);
    }

    public static ErrInformation fail(int errCode,String errMessage)
    {
        return new ErrInformation(errCode,errMessage);
    }

}

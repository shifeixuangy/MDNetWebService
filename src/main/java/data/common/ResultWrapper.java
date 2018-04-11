package data.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2015/4/23.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class ResultWrapper<T> {
    private boolean success;
    private ErrInformation errCode;
    private T data;

    public ResultWrapper()
    {}

    public ResultWrapper(boolean isSuccess,ErrInformation errInfo,T data)
    {
        this.success=isSuccess;
        this.errCode =errInfo;
        this.data=data;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrInformation getErrCode() {
        return errCode;
    }

    public void setErrCode(ErrInformation errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static<U> ResultWrapper<U> success(U data)
    {
        return new ResultWrapper<U>(true,ErrInformation.success(),data);
    }

    public static ResultWrapper serverException(String exceptionMessage)
    {
        return  new ResultWrapper(false,ErrInformation.fail(ErrCode.ERR_SERVER_INTERNAL_ERROR,exceptionMessage),null);
    }

    public static ResultWrapper fail(int errCode)
    {
        return  new ResultWrapper(false,new ErrInformation(errCode,null),null);
    }


}

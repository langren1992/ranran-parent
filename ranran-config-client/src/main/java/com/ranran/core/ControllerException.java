package com.ranran.core;


import com.ranran.core.ErrorCode;

/**
 * 服务层统一异常处理
 *
 * @author 曾睿
 * @create 2017-07-24 21:06
 **/
public class ControllerException extends RuntimeException {

    private ErrorCode errorCode;

    public ControllerException(ErrorCode errorCode) {
        super(errorCode.name);
        errorCode = errorCode;
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(ErrorCode error, Throwable cause) {
        super(error.name, cause);
        errorCode = error;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        if(super.getCause()==null) {
            return super.getMessage();
        }else {
            return super.getMessage() + ", nested exception is " + super.getCause().toString();
        }
    }
}

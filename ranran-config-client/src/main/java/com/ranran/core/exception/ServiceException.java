package com.ranran.core.exception;


import com.ranran.core.ErrorCode;

import javax.persistence.Transient;

/**
 * 服务层统一异常处理
 *
 * @author 曾睿
 * @create 2017-07-24 21:06
 **/
public class ServiceException extends RuntimeException {

    private ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.name);
        errorCode = errorCode;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(ErrorCode error, Throwable cause) {
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

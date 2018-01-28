package com.ranran.core.redis;

import com.ranran.core.ErrorCode;

/**
 * 缓存异常处理类
 */
public class RanRanRedisException extends RuntimeException{

    private ErrorCode errorCode;

    public RanRanRedisException(ErrorCode errorCode) {
        super("异常编码："+errorCode.code+"，异常原因："+errorCode.name);
        errorCode = errorCode;
    }

    public RanRanRedisException(Throwable cause) {
        super(cause);
    }

    public RanRanRedisException(ErrorCode error, Throwable cause) {
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

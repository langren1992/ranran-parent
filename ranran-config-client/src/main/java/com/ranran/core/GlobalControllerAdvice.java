package com.ranran.core;

import com.ranran.core.redis.RanRanRedisException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常统一处理
 */
@ControllerAdvice
public class GlobalControllerAdvice {


    private static Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult errorHandler(Exception ex) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.error = true;
        if (LOGGER.isDebugEnabled()){
            ex.printStackTrace();
        }
        LOGGER.error("\n {}", StringUtils.join(ex.getStackTrace(),"\n    at "));
        if (ex instanceof RanRanRedisException){
            responseResult.message = "系统异常，"+ex.getMessage()+"，请联系管理员！";
        } else if(ex instanceof DuplicateKeyException){
            responseResult.message = "数据唯一异常："+ex.getCause().toString().substring(ex.getCause().toString().indexOf(":")+1);
        } else {
            responseResult.message = "系统异常，请联系管理员！";
        }
        return responseResult;
    }
}

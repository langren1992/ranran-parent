package com.ranran.core;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常统一处理
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult errorHandler(Exception ex) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.success = false;
        responseResult.message = "系统异常，请联系管理员！"+ex.getMessage();
        ex.printStackTrace();
        return responseResult;
    }


}

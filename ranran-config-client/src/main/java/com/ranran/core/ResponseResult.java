package com.ranran.core;

public class ResponseResult {

    /**
     * 启用成功回复
     */
    public static String UPDATE_ENABLE_SUCCESS = "该数据启用成功！";

    /**
     * 启用失败回复
     */
    public static String UPDATE_ENABLE_FAIL = "该数据启用失败！";


    /**
     * 响应消息
     */
    private Boolean success;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应成功
     */
    private Object resultData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}

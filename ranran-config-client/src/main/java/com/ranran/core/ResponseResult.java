package com.ranran.core;

public class ResponseResult {
    /**
     * 响应消息
     */
    public Boolean success;

    /**
     * 系统异常标识
     */
    public Boolean error;

    /**
     * 响应消息
     */
    public String message;

    /**
     * 响应数据
     */
    public Object data;

    /**
     * 配合easyui属性，数据列表total
     */
    public Long total;

    /**
     * 配合easyui属性，数据列表rows
     */
    public Object[] rows;

}

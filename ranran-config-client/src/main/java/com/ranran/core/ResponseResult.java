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
     * 响应数据
     */
    private Object resultData;

    /**
     * 配合easyui属性，数据列表total
     */
    private Long total;

    /**
     * 配合easyui属性，数据列表rows
     */
    public Object[] rows;

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


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object[] getRows() {
        return rows;
    }

    public void setRows(Object[] rows) {
        this.rows = rows;
    }
}

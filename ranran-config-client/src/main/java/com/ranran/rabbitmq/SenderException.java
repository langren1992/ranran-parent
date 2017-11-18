package com.ranran.rabbitmq;

/**
 * 发送异常处理
 *
 * @author 曾睿
 * @create 2017-11-18 16:31
 **/
public class SenderException extends Exception {

    public SenderException(String msg) {
        super(msg);
    }

}

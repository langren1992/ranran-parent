package com.ranran.core;

public class IdWorker {

    private long workerId;
    private long dataCenterId;

    public static Long next(){
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);
        return snowflakeIdWorker.nextId();
    }
}

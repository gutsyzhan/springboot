package com.melody.limitredis.exception;

public class BizException extends RuntimeException{
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int status) {
        this.code = code;
    }

    public BizException(String message){
        super(message);
    }

    public BizException(String message, int code){
        super(message);
        this.code = code;
    }
}

package com.zeno.hsbc.exception;

public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException( Integer code , String message) {
        super(message);
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }
}

package com.zeno.hsbc.exception;

public enum ExceptionType {
    TRANSACTION_NOT_FOUND(1001, "Transaction not found"),
    TRANSACTION_ALREADY_EXISTS(1002, "Transaction already exists"),

    INVALID_ARGS(1003, "Invalid arguments"),

    UNKOWN_ERROR(9999, "Unknown error");

    private final Integer code;
    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

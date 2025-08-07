package com.vttai.Identify.service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "uncategorized"),
    USER_EXISTS(1001, "User already exists"),
    USERNAME_INVALID(1002, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    INVALID_KEY(1004, "Invalid message key"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

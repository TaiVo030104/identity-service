package com.vttai.Identify.service.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTS(1001, "User already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must be at least {min} characters",  HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least {min} characters",   HttpStatus.BAD_REQUEST),
    INVALID_KEY(1004, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed",  HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1007, "Unauthenticated",  HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(1008, "Invalid token" , HttpStatus.BAD_REQUEST),
    USER_EXISTED(1009, "User already exists" ,  HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1010, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_ID_DOB(1011, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}

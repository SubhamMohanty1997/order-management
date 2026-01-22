package com.subham.ordermanagement.user_service.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class ApiError {
    private LocalDateTime timeStamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;

    public ApiError(int statusCode, String error, String message, String path) {
        this.timeStamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}

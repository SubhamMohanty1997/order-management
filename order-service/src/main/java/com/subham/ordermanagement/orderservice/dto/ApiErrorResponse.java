package com.subham.ordermanagement.orderservice.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiErrorResponse {
    private LocalDateTime timeStamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;

    public ApiErrorResponse(int statusCode, String error, String message, String path) {
        this.timeStamp = LocalDateTime.now();
        this.path = path;
        this.message = message;
        this.error = error;
        this.statusCode = statusCode;
    }
}

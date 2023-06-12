package com.myblog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    //setters
    public BlogAPIException(HttpStatus status,String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
    //getters
    public HttpStatus getStatus() {
        return status;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
package com.springboot.linkedin.user_service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime timestamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode){
        this.error = error;
        this.statusCode = statusCode;
    }
}

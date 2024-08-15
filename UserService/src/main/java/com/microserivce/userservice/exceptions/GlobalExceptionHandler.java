package com.microserivce.userservice.exceptions;

import com.microserivce.userservice.payloads.ApiResonse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundExceptions.class})
    public ResponseEntity<ApiResonse> resourceNotFoundExceptionHandler(ResourceNotFoundExceptions exception){
        String message = exception.getMessage();
        ApiResonse apiResonse = new ApiResonse(message, false);
        return new ResponseEntity<>(apiResonse, HttpStatus.BAD_REQUEST);
    }
}

package com.microserivce.ratingservice.exceptions;

import com.microserivce.ratingservice.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException e) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(),false, HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}

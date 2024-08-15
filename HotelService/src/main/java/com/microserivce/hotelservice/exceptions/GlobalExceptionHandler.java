package com.microserivce.hotelservice.exceptions;

import com.microserivce.hotelservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false, HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}

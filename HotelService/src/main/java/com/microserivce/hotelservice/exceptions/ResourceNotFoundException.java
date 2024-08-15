package com.microserivce.hotelservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String id;
    public ResourceNotFoundException(String resourceName, String fieldName, String id) {
        super(String.format("Resource %s not found for field %s and id %s", resourceName, fieldName, id));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.id = id;

    }
}

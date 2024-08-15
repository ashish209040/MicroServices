package com.microserivce.ratingservice.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String id;
    public ResourceNotFoundException(String resourceName, String fieldName, String id) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, id));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.id = id;
    }
}

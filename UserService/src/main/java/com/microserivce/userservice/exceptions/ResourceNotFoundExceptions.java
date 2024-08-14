package com.microserivce.userservice.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundExceptions extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String id;
    public ResourceNotFoundExceptions(String resourceName, String fieldName, String id) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,id));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.id = id;
    }
}

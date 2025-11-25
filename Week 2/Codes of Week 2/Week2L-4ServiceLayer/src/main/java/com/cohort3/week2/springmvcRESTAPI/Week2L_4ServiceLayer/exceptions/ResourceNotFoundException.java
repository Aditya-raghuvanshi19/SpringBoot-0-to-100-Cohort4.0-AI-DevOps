package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

package com.hamitmizrak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 404
// Bulunmadı ==>kendi exception oluşturdum
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    //parametreli constructor
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

package com.hamitmizrak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//404
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepiton extends RuntimeException {

    //parametreli constructor
    public ResourceNotFoundExcepiton(String message) {
        super(message);
    }
}

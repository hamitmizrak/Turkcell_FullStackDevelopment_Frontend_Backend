package com.hamitmizrak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//400
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceBadRequestExcepiton extends RuntimeException {

    //parametreli constructor
    public ResourceBadRequestExcepiton(String message) {
        super(message);
    }
}

package com.hamitmizrak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 400
// yanlış istek atarsak ==>kendi exception oluşturdum
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException {

    //parametreli constructor
    public ResourceBadRequestException(String message) {
        super(message);
    }
}

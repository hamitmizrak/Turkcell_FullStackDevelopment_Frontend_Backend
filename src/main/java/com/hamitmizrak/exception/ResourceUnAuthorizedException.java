package com.hamitmizrak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//401: yetkisiz giriş
//kendi Exception olustum
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class ResourceUnAuthorizedException extends RuntimeException {

    //parametreli constructor
    public ResourceUnAuthorizedException(String message) {
        super(message);
    }
}

package com.hamitmizrak.exception;

//Kendi Exception oluşturdum
public class HamitMizrakException extends RuntimeException {

    //parametreli constructor
    public HamitMizrakException(String message) {
        super(message);
    }
}

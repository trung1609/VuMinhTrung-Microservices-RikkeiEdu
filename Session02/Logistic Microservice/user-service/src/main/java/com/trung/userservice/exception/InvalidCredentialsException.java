package com.trung.userservice.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message){
        super(message);
    }
}

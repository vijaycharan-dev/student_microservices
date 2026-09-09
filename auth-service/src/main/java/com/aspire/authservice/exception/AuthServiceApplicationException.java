package com.aspire.authservice.exception;

public class AuthServiceApplicationException extends RuntimeException{
    public AuthServiceApplicationException(String message){
        super(message);
    }
    public AuthServiceApplicationException(String message, Throwable cause){
        super(message, cause);
    }
}

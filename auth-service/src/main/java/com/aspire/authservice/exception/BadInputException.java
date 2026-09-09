package com.aspire.authservice.exception;

public class BadInputException extends RuntimeException{

    public BadInputException(String message){
        super(message);
    }
    public BadInputException(String message, Throwable cause){
        super(message, cause);
    }

}

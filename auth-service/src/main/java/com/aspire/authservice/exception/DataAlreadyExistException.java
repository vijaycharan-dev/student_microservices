package com.aspire.authservice.exception;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(String message){
        super(message);
    }
    public DataAlreadyExistException(String message,Throwable cause){
        super(message,cause);
    }
}

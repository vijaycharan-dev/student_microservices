package com.aspire.categoryservice.exception;

public class CategoryApplicationException extends RuntimeException{

    public CategoryApplicationException(String message){
        super(message);
    }

    public CategoryApplicationException(String message, Throwable cause){
        super(message, cause);
    }
}

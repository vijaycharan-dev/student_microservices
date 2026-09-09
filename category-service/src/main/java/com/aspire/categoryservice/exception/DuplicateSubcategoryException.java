package com.aspire.categoryservice.exception;

public class DuplicateSubcategoryException extends RuntimeException {
    public DuplicateSubcategoryException(String message) {
        super(message);
    }

    public DuplicateSubcategoryException(String message, Throwable cause){
        super(message, cause);
    }
}
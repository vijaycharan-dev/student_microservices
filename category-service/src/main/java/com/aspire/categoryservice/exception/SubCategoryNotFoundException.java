package com.aspire.categoryservice.exception;

public class SubCategoryNotFoundException extends RuntimeException {
    public SubCategoryNotFoundException(String message) {
        super(message);
    }

    public SubCategoryNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
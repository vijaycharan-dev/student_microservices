package com.aspire.categoryservice.exception;

public class SubCategoryApplicationException extends RuntimeException {
    public SubCategoryApplicationException(String message) {
        super(message);
    }
    public SubCategoryApplicationException(String message, Throwable cause){
        super(message, cause);
    }
}
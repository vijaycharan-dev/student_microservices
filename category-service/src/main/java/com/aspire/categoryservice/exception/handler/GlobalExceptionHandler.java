package com.aspire.categoryservice.exception.handler;

import com.aspire.categoryservice.exception.CategoryApplicationException;
import com.aspire.categoryservice.exception.CategoryNotFoundException;
import com.aspire.categoryservice.exception.DuplicateCategoryException;
import com.aspire.categoryservice.service.dto.CommonResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryApplicationException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleCategoryApplicationException(CategoryApplicationException exception){

        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .data(null)
                .status(500)
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .success(false)
                .build();

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleCategoryNotFoundException(CategoryNotFoundException exception){

        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .status(404)
                .data(null)
                .message(exception.getMessage())
                .success(false)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleDuplicateCategoryException(DuplicateCategoryException exception){

        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .data(null)
                .timestamp(LocalDateTime.now())
                .status(409)
                .success(false)
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleException(Exception exception){

        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .data(null)
                .timestamp(LocalDateTime.now())
                .success(false)
                .status(500)
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
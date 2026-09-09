package com.aspire.categoryservice.exception.handler;

import com.aspire.categoryservice.exception.DuplicateSubcategoryException;
import com.aspire.categoryservice.exception.SubCategoryApplicationException;
import com.aspire.categoryservice.exception.SubCategoryNotFoundException;
import com.aspire.categoryservice.service.dto.CommonResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class SubGlobalExceptionHandler {

    @ExceptionHandler(DuplicateSubcategoryException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleDuplicateSubCategoryException(DuplicateSubcategoryException exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder().data(null).message(exception.getMessage())
                .success(false)
                .timestamp(LocalDateTime.now())
                .status(409)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SubCategoryApplicationException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleSubCategoryApplicationException(SubCategoryApplicationException exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .data(null)
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .success(false)
                .status(500)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SubCategoryNotFoundException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleSubCategoryNotFoundException(SubCategoryNotFoundException exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .data(null)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .success(false)
                .status(404)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleException(Exception exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .data(null)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .success(false)
                .status(500)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
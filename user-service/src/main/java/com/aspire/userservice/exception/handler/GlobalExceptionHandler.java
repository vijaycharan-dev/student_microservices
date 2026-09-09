package com.aspire.userservice.exception.handler;

import com.aspire.userservice.exception.BadRequestException;
import com.aspire.userservice.exception.EmailAlreadyExistException;
import com.aspire.userservice.exception.UserNotFoundException;
import com.aspire.userservice.exception.UserServiceException;
import com.aspire.userservice.service.dto.CommonResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleUserNotFoundException(UserNotFoundException exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .success(false).message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleBadRequestException(BadRequestException exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .success(false).message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleEmailAlreadyExistException(EmailAlreadyExistException exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .success(false).message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleUserServiceException(UserServiceException exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .success(false).message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleException(Exception exception){
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .success(false).message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

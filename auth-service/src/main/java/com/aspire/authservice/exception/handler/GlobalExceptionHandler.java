package com.aspire.authservice.exception.handler;

import com.aspire.authservice.exception.*;
import com.aspire.authservice.service.dto.CommonResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<CommonResponseDTO<Object>> handleBadInput(BadInputException ex){
        CommonResponseDTO<Object> response = CommonResponseDTO.<Object>builder()
                .success(false)
                .statusCode(400)
                .message(ex.getMessage())
                .data(null)
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public  ResponseEntity<CommonResponseDTO<Object>> handleDataNotFound(DataNotFoundException ex){

        CommonResponseDTO<Object> response = CommonResponseDTO.<Object>builder()
                .success(false)
                .statusCode(404)
                .message(ex.getMessage())
                .data(null)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthServiceApplicationException.class)
    public ResponseEntity<CommonResponseDTO<Object>> handleAuthServiceApplication(AuthServiceApplicationException ex){
        CommonResponseDTO<Object> response = CommonResponseDTO.<Object>builder()
                .statusCode(503)
                .message(ex.getMessage())
                .data(null)
                .timeStamp(LocalDateTime.now())
                .success(false)
                .build();

        return  new ResponseEntity<>(response,HttpStatus.SERVICE_UNAVAILABLE);

    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<CommonResponseDTO<Object>> handleDataAlreadyExist(DataAlreadyExistException ex){
        CommonResponseDTO<Object> response = CommonResponseDTO.<Object>builder()
                .statusCode(409)
                .message(ex.getMessage())
                .data(null).
                timeStamp(LocalDateTime.now())
                .success(false)
                .build();

        return  new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }
    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<CommonResponseDTO<Object>> handleInvalidLogin(InvalidLoginException ex){
        CommonResponseDTO<Object> response = CommonResponseDTO.<Object>builder()
                .statusCode(404)
                .message(ex.getMessage())
                .data(null).
                timeStamp(LocalDateTime.now())
                .success(false)
                .build();
        return  new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<CommonResponseDTO<Object>> handleInvalidLogin(InvalidUsernameException ex){
        CommonResponseDTO<Object> response = CommonResponseDTO.<Object>builder()
                .statusCode(404)
                .message(ex.getMessage())
                .data(null).
                timeStamp(LocalDateTime.now())
                .success(false)
                .build();
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleException(Exception exception) {
        CommonResponseDTO<Void> response = CommonResponseDTO.<Void>builder()
                .success(false)
                .message(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

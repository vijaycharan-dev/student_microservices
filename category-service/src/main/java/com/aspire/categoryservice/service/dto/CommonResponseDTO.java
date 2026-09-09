package com.aspire.categoryservice.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommonResponseDTO<T> {

    private T data;
    private String message;
    private boolean success;
    private LocalDateTime timestamp;
    private int status;
}
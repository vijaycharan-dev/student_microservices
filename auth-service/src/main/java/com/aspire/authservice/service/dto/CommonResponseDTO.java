package com.aspire.authservice.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommonResponseDTO <T>{
    private int statusCode;
    private String message;
    private  T data;
    private LocalDateTime timeStamp;
    private  boolean success;

}

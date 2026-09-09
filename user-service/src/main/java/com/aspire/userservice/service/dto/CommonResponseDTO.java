package com.aspire.userservice.service.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class CommonResponseDTO <T>{
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private boolean success;

}

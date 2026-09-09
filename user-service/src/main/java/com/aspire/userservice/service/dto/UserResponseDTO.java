package com.aspire.userservice.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDTO implements Serializable  {
    private Long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String mobileNumber;
    private String dateOfBirth;
    private LocalDateTime createdAt;
    private String status;
    private LocalDateTime updatedAt;

}

package com.aspire.authservice.service.dto;

import com.aspire.authservice.dao.model.Address;
import com.aspire.authservice.dao.model.Gender;
import com.aspire.authservice.dao.model.Role;
import com.aspire.authservice.dao.model.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
    private Gender gender;
    private Role role;
    private UserStatus status;
    private String username;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

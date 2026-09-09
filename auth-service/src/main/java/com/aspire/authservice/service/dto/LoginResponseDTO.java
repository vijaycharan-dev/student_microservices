package com.aspire.authservice.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String accessToken;
    private String emailId;
    private  String userName;
    private  String mobileNumber;
}

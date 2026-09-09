package com.aspire.userservice.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestDTO implements Serializable {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "EmailId is required")
    @Email(message = "Please Provide a valid EmailId Address")
    private String emailId;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=!]).{8,20}$",message = "Password must contain uppercase , lowercase, number and  special character")
    private String password;

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\\\d{4}$", message = "Date of birth must be in the format DD-MM-YYYY")
    private String dateOfBirth;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$",message = "Mobile number must be a valid 10 digit Indian mobile number")
    private String mobileNumber;


}
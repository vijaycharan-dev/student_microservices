package com.aspire.authservice.service.dto;

import com.aspire.authservice.dao.model.Gender;
import com.aspire.authservice.dao.model.Role;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class UserRequestDTO {

    private  String username;
    @NotBlank(message = "FirstName is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private  String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(min = 2, max = 50, message = "last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "EmailId is Required")
    @Email(message = "Please enter a valid email address")
    private String emailId;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$",message = "Mobile number must be a valid 10 digit Indian mobile number")
    private String mobileNumber;

    @NotNull(message = "Gender is Required")
    private Gender gender;

    @NotNull(message = "Role is Required")
    private Role role;

    private String password;
}

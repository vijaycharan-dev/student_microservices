package com.aspire.authservice.service;

import com.aspire.authservice.service.dto.AuthResponseDTO;
import com.aspire.authservice.service.dto.LoginResponseDTO;
import com.aspire.authservice.service.dto.UserRequestDTO;
import com.aspire.authservice.service.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO signUp(UserRequestDTO userRequestDTO);
    LoginResponseDTO signIn(String username, String password);

}

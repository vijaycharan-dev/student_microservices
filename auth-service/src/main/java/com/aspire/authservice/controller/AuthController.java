package com.aspire.authservice.controller;

import com.aspire.authservice.exception.AuthServiceApplicationException;
import com.aspire.authservice.exception.InvalidLoginException;
import com.aspire.authservice.service.UserService;
import com.aspire.authservice.service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<CommonResponseDTO<UserResponseDTO>> signUp(@RequestBody @Valid UserRequestDTO userRequestDTO){
       UserResponseDTO userResponseDTO = userService.signUp(userRequestDTO);
       if(userResponseDTO == null){
           throw new AuthServiceApplicationException("SignUp is failed try again");
       }

       CommonResponseDTO<UserResponseDTO> response = CommonResponseDTO.<UserResponseDTO>builder()
               .statusCode(201)
               .message("UserDetails Successfully Registered")
               .data(userResponseDTO)
               .timeStamp(LocalDateTime.now())
               .success(true)
               .build();


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<CommonResponseDTO<LoginResponseDTO>> signIn(@RequestBody  @Validated LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = userService.signIn(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        if(loginResponseDTO==null){
            throw new InvalidLoginException("username or password wrong");
        }


        return new ResponseEntity<>(CommonResponseDTO.<LoginResponseDTO>builder()
                .statusCode(200)
                .success(true)
                .message("user is successfully login")
                .timeStamp(LocalDateTime.now())
                .data(loginResponseDTO)
                .build(),HttpStatus.OK);
    }

}

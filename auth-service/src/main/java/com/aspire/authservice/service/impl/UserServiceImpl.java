package com.aspire.authservice.service.impl;

import com.aspire.authservice.config.JwtTokenUtil;
import com.aspire.authservice.dao.UserEntityRepository;
import com.aspire.authservice.dao.model.UserEntity;
import com.aspire.authservice.exception.*;
import com.aspire.authservice.service.AuthService;
import com.aspire.authservice.service.EmailService;
import com.aspire.authservice.service.UserService;
import com.aspire.authservice.service.dto.AuthResponseDTO;
import com.aspire.authservice.service.dto.LoginResponseDTO;
import com.aspire.authservice.service.dto.UserRequestDTO;
import com.aspire.authservice.service.dto.UserResponseDTO;
import com.aspire.authservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthService authService;
    private  final UserEntityRepository userEntityRepository;
    private  final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailService emailService;


    @Override
    public UserResponseDTO signUp(UserRequestDTO userRequestDTO) {
        if(userRequestDTO == null){
            throw new BadInputException("UserRequestDTO must be required");
        }
        if(userEntityRepository.existsByUsername(userRequestDTO.getUsername())){
            throw new DataAlreadyExistException("username is already exist"+userRequestDTO.getUsername());
        }
        if(userEntityRepository.existsByemailId(userRequestDTO.getEmailId())){
            throw new DataAlreadyExistException("emailId is already exist"+userRequestDTO.getEmailId());
        }
        UserEntity userEntity=userMapper.toEntity(userRequestDTO);
        if(userEntity != null){
            userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            UserEntity savedUserEntity = userEntityRepository.save(userEntity);
            emailService.sendEmail(userEntity.getEmailId(),"Welcome to AuthService", "Dear user successfully registered");
            return userMapper.toDTO(savedUserEntity);
        }
        throw  new AuthServiceApplicationException("Enable to save the data for singUp please try again");
    }

    @Override
    public LoginResponseDTO signIn(String username, String password) {
        Authentication authentication = authService.authenticationWithCredential(username,password);
        if(authentication == null){
            throw new InvalidLoginException("Username and password Invalid ");
        }
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        if(userEntity == null){
            throw new DataNotFoundException("Enable to find user details");
        }

        return LoginResponseDTO.builder()
                .firstName(userEntity.getFirstName())
                .accessToken(jwtTokenUtil.generateAccessToken(userEntity))
                .build();

    }
}

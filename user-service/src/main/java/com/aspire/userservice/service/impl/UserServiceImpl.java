package com.aspire.userservice.service.impl;

import com.aspire.userservice.exception.EmailAlreadyExistException;
import com.aspire.userservice.exception.UserNotFoundException;
import com.aspire.userservice.repository.UserEntityRepository;
import com.aspire.userservice.repository.entity.UserEntity;
import com.aspire.userservice.service.UserService;
import com.aspire.userservice.service.dto.CommonResponseDTO;
import com.aspire.userservice.service.dto.UserRequestDTO;
import com.aspire.userservice.service.dto.UserResponseDTO;
import com.aspire.userservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    @Override
    public CommonResponseDTO<Long> insertUser(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = userMapper.toEntity(userRequestDTO);
        if (userEntity != null) {
            boolean isEmailExists = userEntityRepository.existsByEmailId(userEntity.getEmailId());
            if(isEmailExists){
                throw  new EmailAlreadyExistException("email already exist" + userEntity.getEmailId());
            }
            UserEntity savedUserEntity = userEntityRepository.save(userEntity);
            if (savedUserEntity != null) {
                return CommonResponseDTO.<Long>builder()
                        .data(savedUserEntity.getUserId())
                        .success(true)
                        .message("user details are successfully inserted")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
        }
        return CommonResponseDTO.<Long>builder()
                .success(false)
                .data(null)
                .message("user insert failed")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public CommonResponseDTO<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = new ArrayList<>();
        List<UserEntity> usersList = userEntityRepository.findAll();
        if (usersList != null) {
            for (UserEntity userEntity : usersList) {
                users.add(userMapper.toDTO(userEntity));
            }
        }
        return CommonResponseDTO.<List<UserResponseDTO>>builder()
                .data(users)
                .message("user details are successfully fetched")
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();

    }

    @Override
    public CommonResponseDTO<UserResponseDTO> getUser(Long userId) {
        Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user details are not found for given userId" + userId);
        }
        return CommonResponseDTO.<UserResponseDTO>builder()
                .success(true)
                .data(userMapper.toDTO(optionalUser.get()))
                .message("user details are successfully fetched")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public void updateUser(Long userId, UserRequestDTO userRequestDTO) {
        Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user details are not found for given userId" + userId);
        }
        UserEntity userEntity = optionalUser.get();
        userMapper.updateEntity(userEntity, userRequestDTO);
        userEntityRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user details are not found for given userId" + userId);
        }
        userEntityRepository.delete(optionalUser.get());
    }
}
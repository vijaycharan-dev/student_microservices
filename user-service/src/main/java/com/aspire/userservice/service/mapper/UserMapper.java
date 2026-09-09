package com.aspire.userservice.service.mapper;

import com.aspire.userservice.exception.BadRequestException;
import com.aspire.userservice.exception.UserNotFoundException;
import com.aspire.userservice.repository.entity.UserEntity;
import com.aspire.userservice.service.dto.UserRequestDTO;
import com.aspire.userservice.service.dto.UserResponseDTO;
import com.aspire.userservice.util.DateUtil;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(UserRequestDTO userRequestDTO){
        if(userRequestDTO == null){
            throw new BadRequestException("UserRequest must not be null");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRequestDTO.getFirstName());
        userEntity.setLastName(userRequestDTO.getLastName());
        userEntity.setEmailId(userRequestDTO.getEmailId());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setMobileNumber(userRequestDTO.getMobileNumber());
        userEntity.setDateOfBirth(DateUtil.parse(userRequestDTO.getDateOfBirth()));
        return userEntity;

    }
    public UserResponseDTO toDTO(UserEntity userEntity){
        if(userEntity == null){
            throw new UserNotFoundException("User Not Found");
        }
        return UserResponseDTO.builder()
                .userId(userEntity.getUserId())
                .dateOfBirth(DateUtil.formate(userEntity.getDateOfBirth()))
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .emailId(userEntity.getEmailId())
                .password(userEntity.getPassword())
                .status(userEntity.getStatus())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .mobileNumber(userEntity.getMobileNumber())
                .build();

    }

    public void updateEntity(UserEntity userEntity, UserRequestDTO userRequestDTO){
        if(userEntity == null){
            throw new UserNotFoundException("User Details not found");
        }

        if(userRequestDTO == null){
            throw new BadRequestException("UserRequest must not be null");
        }
        userEntity.setFirstName(userRequestDTO.getFirstName());
        userEntity.setLastName(userRequestDTO.getLastName());
        userEntity.setEmailId(userRequestDTO.getEmailId());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setMobileNumber(userRequestDTO.getMobileNumber());
        userEntity.setDateOfBirth(DateUtil.parse(userRequestDTO.getDateOfBirth()));

    }
}

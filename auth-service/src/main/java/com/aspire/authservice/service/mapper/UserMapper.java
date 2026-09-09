package com.aspire.authservice.service.mapper;

import com.aspire.authservice.dao.model.UserEntity;
import com.aspire.authservice.service.dto.UserRequestDTO;
import com.aspire.authservice.service.dto.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserRequestDTO userRequestDTO0);
    UserResponseDTO toDTO(UserEntity userEntity);
}

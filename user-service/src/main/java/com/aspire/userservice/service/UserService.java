package com.aspire.userservice.service;

import com.aspire.userservice.service.dto.CommonResponseDTO;
import com.aspire.userservice.service.dto.UserRequestDTO;
import com.aspire.userservice.service.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    public CommonResponseDTO<Long> insertUser(UserRequestDTO userRequestDTO);

    CommonResponseDTO<List<UserResponseDTO>> getUsers();

    CommonResponseDTO<UserResponseDTO> getUser(Long userId);

    void updateUser(Long userId, UserRequestDTO userRequestDTO);

    void deleteUser(Long userId);
}
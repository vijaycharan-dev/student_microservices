package com.aspire.authservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aspire.authservice.config.JwtTokenUtil;
import com.aspire.authservice.dao.model.Gender;
import com.aspire.authservice.dao.model.Role;
import com.aspire.authservice.service.UserService;
import com.aspire.authservice.service.dto.LoginRequestDTO;
import com.aspire.authservice.service.dto.LoginResponseDTO;
import com.aspire.authservice.service.dto.UserRequestDTO;
import com.aspire.authservice.service.dto.UserResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signUpTest() throws Exception {

        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setFirstName("Avinash");
        userRequestDTO.setLastName("Babu");
        userRequestDTO.setEmailId("avinash@gmail.com");
        userRequestDTO.setPassword("Avinash@11");
        userRequestDTO.setUsername("avinash");
        userRequestDTO.setMobileNumber("6281807771");
        userRequestDTO.setGender(Gender.MALE);
        userRequestDTO.setRole(Role.USER);

        String requestBody =
                objectMapper.writeValueAsString(userRequestDTO);

        UserResponseDTO response =
                new UserResponseDTO();

        Mockito.when(
                userService.signUp(any(UserRequestDTO.class))
        ).thenReturn(response);

        mockMvc.perform(
                        post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void signUpFailureTest() throws Exception {

        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setFirstName("Vijay");
        userRequestDTO.setLastName("Charan");
        userRequestDTO.setEmailId("vijay@gmail.com");
        userRequestDTO.setPassword("vijay@11");
        userRequestDTO.setUsername("vijay1");
        userRequestDTO.setMobileNumber("8309465499");
        userRequestDTO.setGender(Gender.MALE);
        userRequestDTO.setRole(Role.USER);

        String requestBody =
                objectMapper.writeValueAsString(userRequestDTO);

        Mockito.when(
                userService.signUp(any(UserRequestDTO.class))
        ).thenReturn(null);

        mockMvc.perform(
                        post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void signInTest() throws Exception {

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();

        loginRequestDTO.setUsername("vijay@gmail.com");
        loginRequestDTO.setPassword("vijay@1");

        String requestBody =
                objectMapper.writeValueAsString(loginRequestDTO);

        LoginResponseDTO response =
                LoginResponseDTO.builder()
                        .userId(1L)
                        .firstName("Vijay")
                        .lastName("Charan")
                        .accessToken("dummy-access-token")
                        .emailId("vijay@gmail.com")
                        .userName("Vijay@123")
                        .build();

        Mockito.when(
                userService.signIn(anyString(), anyString())
        ).thenReturn(response);

        mockMvc.perform(
                        post("/api/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk());
    }

    @Test
    void signInFailureTest() throws Exception {

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();

        loginRequestDTO.setUsername("vijay@gmail.com");
        loginRequestDTO.setPassword("vijay@1");

        String requestBody =
                objectMapper.writeValueAsString(loginRequestDTO);

        Mockito.when(
                userService.signIn(anyString(), anyString())
        ).thenReturn(null);

        mockMvc.perform(
                        post("/api/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isUnauthorized());
    }
}
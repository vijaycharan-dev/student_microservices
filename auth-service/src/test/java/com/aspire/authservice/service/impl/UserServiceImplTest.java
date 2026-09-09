package com.aspire.authservice.service.impl;

import com.aspire.authservice.config.JwtTokenUtil;
import com.aspire.authservice.dao.UserEntityRepository;
import com.aspire.authservice.dao.model.UserEntity;
import com.aspire.authservice.exception.AuthServiceApplicationException;
import com.aspire.authservice.exception.*;
import com.aspire.authservice.service.AuthService;
import com.aspire.authservice.service.dto.LoginResponseDTO;
import com.aspire.authservice.service.dto.UserRequestDTO;
import com.aspire.authservice.service.dto.UserResponseDTO;
import com.aspire.authservice.service.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class UserServiceImplTest {

    private final AuthService authService =
            Mockito.mock(AuthService.class);

    private final UserEntityRepository userEntityRepository =
            Mockito.mock(UserEntityRepository.class);

    private final UserMapper userMapper =
            Mockito.mock(UserMapper.class);

    private final PasswordEncoder passwordEncoder =
            Mockito.mock(PasswordEncoder.class);

    private final JwtTokenUtil jwtTokenUtil =
            Mockito.mock(JwtTokenUtil.class);

    private final UserServiceImpl userService =
            new UserServiceImpl(
                    authService,
                    userEntityRepository,
                    userMapper,
                    passwordEncoder,
                    jwtTokenUtil
            );



    @Test
    void signUpNullRequestTest() {

        assertThrows(
                BadInputException.class,
                () -> userService.signUp(null)
        );
    }



    @Test
    void signUpUsernameAlreadyExistsTest() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setUsername("vijay");
        userRequestDTO.setEmailId("vijay@gmail.com");

        Mockito.when(
                userEntityRepository.existsByUsername("vijay")
        ).thenReturn(true);

        assertThrows(
                DataAlreadyExistException.class,
                () -> userService.signUp(userRequestDTO)
        );
    }



    @Test
    void signUpEmailAlreadyExistsTest() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setUsername("vijay");
        userRequestDTO.setEmailId("vijay@gmail.com");

        Mockito.when(
                userEntityRepository.existsByUsername("vijay")
        ).thenReturn(false);

        Mockito.when(
                userEntityRepository.existsByemailId("vijay@gmail.com")
        ).thenReturn(true);

        assertThrows(
                DataAlreadyExistException.class,
                () -> userService.signUp(userRequestDTO)
        );
    }



    @Test
    void signUpTest() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setUsername("vijay");
        userRequestDTO.setEmailId("vijay@gmail.com");
        userRequestDTO.setPassword("vijay@1");

        UserEntity userEntity = new UserEntity();
        UserEntity savedUserEntity = new UserEntity();

        UserResponseDTO userResponseDTO =
                new UserResponseDTO();

        Mockito.when(
                userEntityRepository.existsByUsername(anyString())
        ).thenReturn(false);

        Mockito.when(
                userEntityRepository.existsByemailId(anyString())
        ).thenReturn(false);

        Mockito.when(
                userMapper.toEntity(any(UserRequestDTO.class))
        ).thenReturn(userEntity);

        Mockito.when(
                passwordEncoder.encode(anyString())
        ).thenReturn("encodedPassword");

        Mockito.when(
                userEntityRepository.save(userEntity)
        ).thenReturn(savedUserEntity);

        Mockito.when(
                userMapper.toDTO(savedUserEntity)
        ).thenReturn(userResponseDTO);

        UserResponseDTO response =
                userService.signUp(userRequestDTO);

        assertEquals(userResponseDTO, response);

        Mockito.verify(userEntityRepository).save(userEntity);
        Mockito.verify(passwordEncoder).encode("vijay@1");
        Mockito.verify(userMapper).toDTO(savedUserEntity);
    }



    @Test
    void signUpMapperReturnsNullTest() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setUsername("vijay");
        userRequestDTO.setEmailId("vijay@gmail.com");
        userRequestDTO.setPassword("vijay@1");

        Mockito.when(
                userEntityRepository.existsByUsername(anyString())
        ).thenReturn(false);

        Mockito.when(
                userEntityRepository.existsByemailId(anyString())
        ).thenReturn(false);

        Mockito.when(
                userMapper.toEntity(any(UserRequestDTO.class))
        ).thenReturn(null);

        assertThrows(
                AuthServiceApplicationException.class,
                () -> userService.signUp(userRequestDTO)
        );
    }



    @Test
    void signInAuthenticationNullTest() {

        Mockito.when(
                authService.authenticationWithCredential(
                        anyString(),
                        anyString()
                )
        ).thenReturn(null);

        assertThrows(
                InvalidLoginException.class,
                () -> userService.signIn(
                        "vijay",
                        "vijay@1"
                )
        );
    }



    @Test
    void signInUserNullTest() {

        Authentication authentication =
                Mockito.mock(Authentication.class);

        Mockito.when(
                authService.authenticationWithCredential(
                        anyString(),
                        anyString()
                )
        ).thenReturn(authentication);

        Mockito.when(
                authentication.getPrincipal()
        ).thenReturn(null);

        assertThrows(
                DataNotFoundException.class,
                () -> userService.signIn(
                        "vijay",
                        "vijay@1"
                )
        );
    }



    @Test
    void signInTest() {

        Authentication authentication =
                Mockito.mock(Authentication.class);

        UserEntity userEntity =
                new UserEntity();

        userEntity.setFirstName("vijay");
        userEntity.setLastName("charan");
        userEntity.setEmailId("vijay@gmail.com");
        userEntity.setUsername("vijay");

        Mockito.when(
                authService.authenticationWithCredential(
                        anyString(),
                        anyString()
                )
        ).thenReturn(authentication);

        Mockito.when(
                authentication.getPrincipal()
        ).thenReturn(userEntity);

        Mockito.when(
                jwtTokenUtil.generateAccessToken(userEntity)
        ).thenReturn("dummy-access-token");

        LoginResponseDTO response =
                userService.signIn(
                        "vijay",
                        "vijay@1"
                );

        assertEquals("vijay", response.getFirstName());
        assertEquals("dummy-access-token", response.getAccessToken());

        Mockito.verify(
                authService
        ).authenticationWithCredential(
                "vijay",
                "vijay@1"
        );

        Mockito.verify(
                jwtTokenUtil
        ).generateAccessToken(userEntity);
    }
}
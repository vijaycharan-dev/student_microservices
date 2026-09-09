package com.aspire.authservice.service.impl;

import com.aspire.authservice.dao.UserEntityRepository;
import com.aspire.authservice.dao.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthServiceDetailsImplTest {

    private final UserEntityRepository userEntityRepository =
            Mockito.mock(UserEntityRepository.class);

    private final AuthServiceDetailsImpl authServiceDetails =
            new AuthServiceDetailsImpl(userEntityRepository);

    @Test
    void loadUserByUsernameTest() {

        String username = "vijay";

        UserEntity userEntity = new UserEntity();

        Mockito.when(
                userEntityRepository.findByUsername(username)
        ).thenReturn(Optional.of(userEntity));

        UserDetails response =
                authServiceDetails.loadUserByUsername(username);

        assertEquals(userEntity, response);

        Mockito.verify(
                userEntityRepository
        ).findByUsername(username);
    }



    @Test
    void loadUserByUsernameUserNotFoundTest() {

        String username = "vijay";

        Mockito.when(
                userEntityRepository.findByUsername(username)
        ).thenReturn(Optional.empty());

        UsernameNotFoundException exception =
                assertThrows(
                        UsernameNotFoundException.class,
                        () -> authServiceDetails.loadUserByUsername(username)
                );

        assertEquals(
                "User is not found for username : " + username,
                exception.getMessage()
        );

        Mockito.verify(
                userEntityRepository
        ).findByUsername(username);
    }
}
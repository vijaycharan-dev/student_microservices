package com.aspire.authservice.service.impl;

import com.aspire.authservice.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthServiceImplTest {

    private final AuthenticationManager authenticationManager =
            Mockito.mock(AuthenticationManager.class);

    private final AuthService authService =
            new AuthServiceImp(authenticationManager);


    @Test
    void authenticateWithCredentialsTest() {

        String username = "vijay";
        String password = "vijay@1";

        Authentication authentication =
                Mockito.mock(Authentication.class);

        Mockito.when(
                authenticationManager.authenticate(
                        Mockito.any(UsernamePasswordAuthenticationToken.class)
                )
        ).thenReturn(authentication);

        Authentication response =
                authService.authenticationWithCredential(
                        username,
                        password
                );

        assertEquals(authentication, response);

        Mockito.verify(
                authenticationManager
        ).authenticate(
                Mockito.any(UsernamePasswordAuthenticationToken.class)
        );
    }
}
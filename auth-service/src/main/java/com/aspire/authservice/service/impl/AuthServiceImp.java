package com.aspire.authservice.service.impl;

import com.aspire.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication authenticationWithCredential(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }
}

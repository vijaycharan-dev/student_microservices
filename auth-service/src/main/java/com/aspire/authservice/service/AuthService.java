package com.aspire.authservice.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication authenticationWithCredential( String username, String password);
}

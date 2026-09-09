package com.aspire.authservice.service.impl;

import com.aspire.authservice.dao.UserEntityRepository;
import com.aspire.authservice.dao.model.UserEntity;
import com.aspire.authservice.service.AuthServiceDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceDetailsImpl implements AuthServiceDetails {


    private final UserEntityRepository userEntityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userEntityRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw  new UsernameNotFoundException("User is not found for username : " + username);

        }
        return optionalUser.get();
    }
}

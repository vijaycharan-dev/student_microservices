package com.aspire.authservice.dao;

import com.aspire.authservice.dao.model.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends  PersonEntityRepository<UserEntity> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByemailId(@NotBlank(message = "EmailId is Required") @Email(message = "Please enter a valid email address") String emailId);

}

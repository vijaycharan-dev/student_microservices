package com.aspire.userservice.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "USER_TL")
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FIRST_NAME",length = 50,nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME",length = 50,nullable = false)
    private String lastName;

    @Column(name = "EMAIL_ID",length = 100,nullable = false)
    private String emailId;

    @Column(name = "PASSWORD",length = 50,nullable = false)
    private String password;

    @Column(name = "DATE_OF_BIRTH",nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "MOBILE_NUMBER",length = 20,nullable = false)
    private String mobileNumber;

    @Column(name = "STATUS",nullable = false)
    private String status;

    @Column(name = "CREATED_AT",updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT",nullable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.status = "Active";
        this.createdAt = LocalDateTime.now();

    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}

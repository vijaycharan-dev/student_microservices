package com.aspire.authservice.dao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"emailId"})})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long userId;

     @Column(name = "FIRST_NAME",length = 50,nullable = false)
     private String firstName;

     @Column(name = "LAST_NAME",nullable = false,length = 50)
     private String lastName;

     @Column(name = "EMAIL_ID",nullable = false,length = 100)
     private String emailId;

     @Enumerated(EnumType.STRING)
     private Gender gender;

     @Enumerated(EnumType.STRING)
     private Role role;

     @Enumerated(EnumType.STRING)
     private UserStatus status;

     private  long createdBy;
     private long updatedBy;
     @Column(updatable = false)
     private LocalDateTime createAt;
     private LocalDateTime updatedAt;

     @PrePersist
     protected void onCreate(){
         createAt =LocalDateTime.now();
         status = UserStatus.PENDING;

     }

     @PreUpdate
     protected  void onUpdate(){
         updatedAt = LocalDateTime.now();

     }



}

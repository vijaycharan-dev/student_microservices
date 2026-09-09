package com.aspire.categoryservice.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORY_TL")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 50)
    private String categoryName;

    @Column(name = "CATEGORY_CODE", nullable = false, length = 50)
    private String categoryCode;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(name = "STATUS", nullable = false)
    private String status;

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
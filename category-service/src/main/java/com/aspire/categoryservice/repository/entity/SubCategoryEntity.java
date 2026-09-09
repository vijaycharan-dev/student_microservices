package com.aspire.categoryservice.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "SUBCATEGORY_TL")
@Data
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    @Column(name = "SUBCATEGORY_NAME", nullable = false, length = 50)
    private String subCategoryName;

    @Column(name = "SUBCATEGORY_DESCRIPTION", nullable = false)
    private String subCategoryDescription;

    @Column(name = "SUBCATEGORY_CODE", nullable = false)
    private String subCategoryCode;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @PrePersist
    protected void onCreate() {
        this.status = "Active";
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
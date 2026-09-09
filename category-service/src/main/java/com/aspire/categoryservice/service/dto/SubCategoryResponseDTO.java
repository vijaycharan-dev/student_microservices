package com.aspire.categoryservice.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SubCategoryResponseDTO {

    private Long subCategoryId;
    private String subCategoryName;
    private String subCategoryDescription;
    private String subCategoryCode;
    private Long createdBy;
    private Long updatedBy;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
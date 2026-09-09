package com.aspire.categoryservice.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CategoryResponseDTO {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryCode;
    private Long createdBy;
    private Long updatedBy;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
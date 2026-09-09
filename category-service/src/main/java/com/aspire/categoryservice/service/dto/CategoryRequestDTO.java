package com.aspire.categoryservice.service.dto;

import lombok.Data;

@Data
public class CategoryRequestDTO {

    private String categoryName;
    private String categoryCode;
    private String categoryDescription;
    private String status;
}
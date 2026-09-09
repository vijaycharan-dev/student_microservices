package com.aspire.categoryservice.service.dto;

import lombok.Data;

@Data
public class SubCategoryRequestDTO {

    private String subCategoryName;
    private String subCategoryCode;
    private String subCategoryDescription;
    private String status;
}
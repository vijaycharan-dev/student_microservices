package com.aspire.categoryservice.service;

import com.aspire.categoryservice.service.dto.SubCategoryRequestDTO;
import com.aspire.categoryservice.service.dto.SubCategoryResponseDTO;

import java.util.List;

public interface SubCategoryService {

    SubCategoryResponseDTO createSubCategory(SubCategoryRequestDTO subCategoryRequestDTO);

    List<SubCategoryResponseDTO> getSubCategories();

    SubCategoryResponseDTO getSubCategory(Long subCategoryId);

    SubCategoryResponseDTO updateSubCategory(Long subCategoryId, SubCategoryRequestDTO subCategoryRequestDTO);

    void deleteSubCategory(Long subCategoryId);
}
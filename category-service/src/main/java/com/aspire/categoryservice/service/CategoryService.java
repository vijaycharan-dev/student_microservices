package com.aspire.categoryservice.service;

import com.aspire.categoryservice.service.dto.CategoryRequestDTO;
import com.aspire.categoryservice.service.dto.CategoryResponseDTO;
import com.aspire.categoryservice.service.dto.CommonResponseDTO;

import java.util.List;


public interface CategoryService {

    public CommonResponseDTO<Long> createCategory(CategoryRequestDTO categoryRequestDTO);

    public CommonResponseDTO<List<CategoryResponseDTO>> getCategories();

    public CommonResponseDTO<CategoryResponseDTO> getCategory(Long categoryId);

    public void updateCategory(CategoryRequestDTO categoryRequestDTO, Long categoryId);

    public void deleteCategory(Long categoryId);

    List<CategoryResponseDTO> getCategorysList();

}
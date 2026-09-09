package com.aspire.categoryservice.service.impl;

import com.aspire.categoryservice.exception.CategoryNotFoundException;
import com.aspire.categoryservice.exception.DuplicateCategoryException;
import com.aspire.categoryservice.repository.CategoryEntityRepository;
import com.aspire.categoryservice.repository.entity.CategoryEntity;
import com.aspire.categoryservice.service.CategoryService;
import com.aspire.categoryservice.service.dto.CategoryRequestDTO;
import com.aspire.categoryservice.service.dto.CategoryResponseDTO;
import com.aspire.categoryservice.service.dto.CommonResponseDTO;
import com.aspire.categoryservice.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryEntityRepository categoryEntityRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public CommonResponseDTO<Long> createCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryRequestDTO);

        if (categoryEntity == null) {
            return CommonResponseDTO.<Long>builder()
                    .data(null)
                    .success(false)
                    .timestamp(LocalDateTime.now())
                    .message("Category creation failed")
                    .build();
        }

        if (categoryEntityRepository.existsByCategoryName(categoryEntity.getCategoryName())) {
            throw new DuplicateCategoryException("This category already exists");
        }

        CategoryEntity savedCategoryEntity = categoryEntityRepository.save(categoryEntity);

        return CommonResponseDTO.<Long>builder()
                .success(true)
                .data(savedCategoryEntity.getCategoryId())
                .timestamp(LocalDateTime.now())
                .message("Category details successfully created")
                .build();
    }


    @Override
    public CommonResponseDTO<List<CategoryResponseDTO>> getCategories() {
        List<CategoryResponseDTO> categories = new ArrayList<>();
        List<CategoryEntity> categoriesList = categoryEntityRepository.findAll();

        if (categoriesList != null){
            for(CategoryEntity categoryEntity : categoriesList){
                categories.add(categoryMapper.toDto(categoryEntity));
            }
        }
        return CommonResponseDTO.<List<CategoryResponseDTO>>builder()
                .message("Categories fetched successfully")
                .success(true).timestamp(LocalDateTime.now())
                .data(categories).build();
    }

    @Override
    public CommonResponseDTO<CategoryResponseDTO> getCategory(Long categoryId) {
        Optional<CategoryEntity> optionalCategory = categoryEntityRepository.findById(categoryId);

        if (optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("Category not found for the given category id "+ categoryId);
        }

        return CommonResponseDTO.<CategoryResponseDTO>builder().data(categoryMapper.toDto(optionalCategory.get()))
                .success(true).message("Category details are fetched successfully for the given category id").build();
    }

    @Override
    public void updateCategory(CategoryRequestDTO categoryRequestDTO, Long categoryId) {
        Optional<CategoryEntity> optionalCategory = categoryEntityRepository.findById(categoryId);
        if (optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("category details not found");
        }

        CategoryEntity categoryEntity =optionalCategory.get();
        categoryMapper.updateEntity(categoryRequestDTO, categoryEntity);
        categoryEntityRepository.save(categoryEntity);


    }

    @Override
    public void deleteCategory(Long categoryId) {

        Optional<CategoryEntity> optionalCategory = categoryEntityRepository.findById(categoryId);

        if (optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("Category not found for the given categoryId "+ categoryId);
        }
        categoryEntityRepository.delete(optionalCategory.get());

    }

    @Override
    public List<CategoryResponseDTO> getCategorysList() {
        List<CategoryResponseDTO> categories = new ArrayList<>();
        List<CategoryEntity> categoriesList = categoryEntityRepository.findAll();

        if (categoriesList != null){
            for(CategoryEntity categoryEntity : categoriesList){
                categories.add(categoryMapper.toDto(categoryEntity));
            }
        }
        return categories;
    }
}
package com.aspire.categoryservice.service.impl;

import com.aspire.categoryservice.exception.DuplicateSubcategoryException;
import com.aspire.categoryservice.exception.SubCategoryNotFoundException;
import com.aspire.categoryservice.repository.SubCategoryEntityRepository;
import com.aspire.categoryservice.repository.entity.SubCategoryEntity;
import com.aspire.categoryservice.service.SubCategoryService;
import com.aspire.categoryservice.service.dto.SubCategoryRequestDTO;
import com.aspire.categoryservice.service.dto.SubCategoryResponseDTO;
import com.aspire.categoryservice.service.mapper.SubCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryEntityRepository subCategoryEntityRepository;
    private final SubCategoryMapper subCategoryMapper;

    @Override
    public SubCategoryResponseDTO createSubCategory(
            SubCategoryRequestDTO subCategoryRequestDTO) {

        if (subCategoryEntityRepository.existsBySubCategoryName(
                subCategoryRequestDTO.getSubCategoryName())) {

            throw new DuplicateSubcategoryException("SubCategory name already exists: " + subCategoryRequestDTO.getSubCategoryName());
        }

        SubCategoryEntity subCategoryEntity =
                subCategoryMapper.toEntity(subCategoryRequestDTO);

        SubCategoryEntity savedSubCategoryEntity = subCategoryEntityRepository.save(subCategoryEntity);

        return subCategoryMapper.toDto(savedSubCategoryEntity);
    }

    @Override
    public List<SubCategoryResponseDTO> getSubCategories() {

        List<SubCategoryResponseDTO> response = new ArrayList<>();

        List<SubCategoryEntity> subCategoryEntityList =
                subCategoryEntityRepository.findAll();

        for (SubCategoryEntity subCategoryEntity : subCategoryEntityList) {
            response.add(subCategoryMapper.toDto(subCategoryEntity));
        }

        return response;
    }

    @Override
    public SubCategoryResponseDTO getSubCategory(Long subCategoryId) {

        Optional<SubCategoryEntity> optionalSubCategory =
                subCategoryEntityRepository.findById(subCategoryId);

        if (optionalSubCategory.isEmpty()) {
            throw new SubCategoryNotFoundException(
                    "SubCategory is not found for given subCategoryId: "
                            + subCategoryId);
        }

        return subCategoryMapper.toDto(optionalSubCategory.get());
    }

    @Override
    public SubCategoryResponseDTO updateSubCategory(Long subCategoryId, SubCategoryRequestDTO subCategoryRequestDTO) {
        Optional<SubCategoryEntity> optionalSubCategory =
                subCategoryEntityRepository.findById(subCategoryId);

        if (optionalSubCategory.isEmpty()) {
            throw new SubCategoryNotFoundException(
                    "SubCategory is not found for given subCategoryId: "
                            + subCategoryId);
        }

        SubCategoryEntity subCategoryEntity =
                optionalSubCategory.get();

        subCategoryMapper.updateEntity(
                subCategoryRequestDTO,
                subCategoryEntity);

        SubCategoryEntity updatedSubCategory =
                subCategoryEntityRepository.save(subCategoryEntity);

        return subCategoryMapper.toDto(updatedSubCategory);
    }

    @Override
    public void deleteSubCategory(Long subCategoryId) {

        Optional<SubCategoryEntity> optionalSubCategory =
                subCategoryEntityRepository.findById(subCategoryId);

        if (optionalSubCategory.isEmpty()) {
            throw new SubCategoryNotFoundException("SubCategory details are not found for given subCategoryId: " + subCategoryId);
        }

        subCategoryEntityRepository.delete(optionalSubCategory.get());
    }
}
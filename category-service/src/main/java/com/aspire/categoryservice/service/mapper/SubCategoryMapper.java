package com.aspire.categoryservice.service.mapper;

import com.aspire.categoryservice.repository.entity.SubCategoryEntity;
import com.aspire.categoryservice.service.dto.SubCategoryRequestDTO;
import com.aspire.categoryservice.service.dto.SubCategoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    SubCategoryEntity toEntity(SubCategoryRequestDTO subCategoryRequestDTO);

    SubCategoryResponseDTO toDto(SubCategoryEntity subCategoryEntity);

    void updateEntity(SubCategoryRequestDTO subCategoryRequestDTO, @MappingTarget SubCategoryEntity subCategoryEntity);
}
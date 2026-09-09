package com.aspire.categoryservice.service.mapper;

import com.aspire.categoryservice.repository.entity.CategoryEntity;
import com.aspire.categoryservice.service.dto.CategoryRequestDTO;
import com.aspire.categoryservice.service.dto.CategoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity toEntity(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO toDto(CategoryEntity categoryEntity);

    void updateEntity(CategoryRequestDTO categoryRequestDTO, @MappingTarget CategoryEntity categoryEntity);


}
package com.aspire.categoryservice.controller;

import com.aspire.categoryservice.service.SubCategoryService;
import com.aspire.categoryservice.service.dto.CommonResponseDTO;
import com.aspire.categoryservice.service.dto.SubCategoryRequestDTO;
import com.aspire.categoryservice.service.dto.SubCategoryResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping
    public ResponseEntity<CommonResponseDTO<SubCategoryResponseDTO>> createSubCategory(
            @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) {

        SubCategoryResponseDTO subCategoryResponseDTO =
                subCategoryService.createSubCategory(subCategoryRequestDTO);

        CommonResponseDTO<SubCategoryResponseDTO> response =
                CommonResponseDTO.<SubCategoryResponseDTO>builder()
                        .message("SubCategory details are successfully inserted")
                        .data(subCategoryResponseDTO)
                        .status(HttpStatus.CREATED.value())
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommonResponseDTO<List<SubCategoryResponseDTO>>> getSubCategories() {

        List<SubCategoryResponseDTO> subCategories =
                subCategoryService.getSubCategories();

        CommonResponseDTO<List<SubCategoryResponseDTO>> response =
                CommonResponseDTO.<List<SubCategoryResponseDTO>>builder()
                        .message("SubCategory details are successfully fetched")
                        .data(subCategories)
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<CommonResponseDTO<SubCategoryResponseDTO>> getSubCategory(
            @PathVariable("subCategoryId") Long subCategoryId) {

        SubCategoryResponseDTO subCategory =
                subCategoryService.getSubCategory(subCategoryId);

        CommonResponseDTO<SubCategoryResponseDTO> response =
                CommonResponseDTO.<SubCategoryResponseDTO>builder()
                        .message("SubCategory details are successfully fetched")
                        .data(subCategory)
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{subCategoryId}")
    public ResponseEntity<CommonResponseDTO<SubCategoryResponseDTO>> updateSubCategory(
            @PathVariable("subCategoryId") Long subCategoryId,
            @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) {

        SubCategoryResponseDTO updatedSubCategory = subCategoryService.updateSubCategory(
                subCategoryId,
                subCategoryRequestDTO);

        CommonResponseDTO<SubCategoryResponseDTO> response =
                CommonResponseDTO.<SubCategoryResponseDTO>builder()
                        .message("SubCategory details are successfully updated")
                        .data(updatedSubCategory)
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{subCategoryId}")
    public ResponseEntity<Void> deleteSubCategory(
            @PathVariable("subCategoryId") Long subCategoryId) {

        subCategoryService.deleteSubCategory(subCategoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
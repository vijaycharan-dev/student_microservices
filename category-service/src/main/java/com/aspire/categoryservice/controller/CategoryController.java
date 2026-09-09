package com.aspire.categoryservice.controller;

import com.aspire.categoryservice.service.CategoryService;
import com.aspire.categoryservice.service.dto.CategoryRequestDTO;
import com.aspire.categoryservice.service.dto.CategoryResponseDTO;
import com.aspire.categoryservice.service.dto.CommonResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CommonResponseDTO<Long>> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommonResponseDTO<List<CategoryResponseDTO>>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CommonResponseDTO<CategoryResponseDTO>> getCategory(@PathVariable("categoryId") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO){
        categoryService.updateCategory(categoryRequestDTO, categoryId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
package com.aspire.categoryservice.controller;

import com.aspire.categoryservice.service.CategoryService;
import com.aspire.categoryservice.service.ExcelService;
import com.aspire.categoryservice.service.dto.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/excel")
public class  ExcelController {
    private final ExcelService excelService;
    private final CategoryService categoryService;
    @GetMapping("/{password}")
    public ResponseEntity<byte[]> getExcelData(@PathVariable("password") String password) {
        List<CategoryResponseDTO> categorys = categoryService.getCategorysList();
        try {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=categories.xlsx")
                    .body(excelService.generateEncryptedCategorys(categorys, password).toByteArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

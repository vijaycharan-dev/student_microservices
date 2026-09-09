package com.aspire.categoryservice.controller;

import com.aspire.categoryservice.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(fileStorageService.storeFile(file), HttpStatus.CREATED);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String fileName){
        return new ResponseEntity<>(fileStorageService.loadFileAsResource(fileName), HttpStatus.OK);
    }
}
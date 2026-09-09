package com.aspire.categoryservice.controller;

import com.aspire.categoryservice.service.AzureFileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/azure/file")
@RequiredArgsConstructor
public class AzureFileStorageController {

    private final AzureFileStorageService azureFileStorageService;
    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileurl = azureFileStorageService.uploadImage(file);
            Map<String, String> response = new LinkedHashMap<>();
            response.put("fileurl", fileurl);
            response.put("message", "File is successfully uploaded");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> response = new LinkedHashMap<>();
            response.put("message", "Unable to upload file");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("url") String url) {
        String fileName = url.substring(url.lastIndexOf('/')+1);
        byte[] fileData = azureFileStorageService.downloadFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename="+fileName);
        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFileNyFileName(@PathVariable("fileName") String fileName) {
        byte[] fileData = azureFileStorageService.downloadFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename="+fileName);
        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);

    }
}

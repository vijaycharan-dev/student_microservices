package com.aspire.categoryservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AzureFileStorageService {
    public String uploadImage(MultipartFile file) throws IOException;
    public byte[] downloadFile(String fileName);
}

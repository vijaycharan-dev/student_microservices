package com.aspire.categoryservice.service.impl;


import com.aspire.categoryservice.service.AzureFileStorageService;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AzureFileStorageServiceImpl implements AzureFileStorageService {
    @Value("${azure.storage.sas-token}")
    private String sasToken;
    @Value("${azure.storage.blob-endpoint}")
    private String blobEndPoint;
    @Value("${azure.storage.container-name}")
    private String containerName;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS"));
        String originalFileName = file.getOriginalFilename();
        String newFilename = originalFileName != null ?
                originalFileName.replaceFirst("(\\.[^.]+)$", "_" + timestamp + "$1") :
                "file_" + timestamp;
        BlobContainerClient containerClient = new BlobContainerClientBuilder().endpoint(blobEndPoint).sasToken(containerName).buildClient();
        BlobClient blobClient = containerClient.getBlobClient(newFilename);
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        return blobClient.getBlobUrl();
    }

    @Override
    public byte[] downloadFile(String fileName) {
        BlobContainerClient containerClient = new BlobContainerClientBuilder().endpoint(blobEndPoint).sasToken(containerName).buildClient();
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        blobClient.download(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}

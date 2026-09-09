package com.aspire.categoryservice.service.impl;

import com.aspire.categoryservice.exception.CategoryApplicationException;
import com.aspire.categoryservice.properties.FileStorageProperties;
import com.aspire.categoryservice.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir());

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new CategoryApplicationException("Exception rised upload folder creation", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")){
                throw new CategoryApplicationException("Sorry file name contains invalid path sequence"+fileName);

            }
            Path targetLocation = this.fileStorageLocation.resolve(timestamp + fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return timestamp + fileName;

        } catch (Exception e) {
            throw new CategoryApplicationException("Exception rised while file uploading please try again",e);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()){

                return resource;
            }else {
                throw new CategoryApplicationException("File is not found");
            }

        } catch (Exception e) {
            throw new CategoryApplicationException("Exception rised while file downloading");
        }

    }
}
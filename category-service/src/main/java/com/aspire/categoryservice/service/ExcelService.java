package com.aspire.categoryservice.service;

import com.aspire.categoryservice.service.dto.CategoryResponseDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface ExcelService {
    public ByteArrayOutputStream generateEncryptedCategorys(List<CategoryResponseDTO> categorys, String password) throws IOException, InvalidFormatException, GeneralSecurityException;
}

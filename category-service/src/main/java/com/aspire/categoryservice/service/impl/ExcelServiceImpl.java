package com.aspire.categoryservice.service.impl;

import com.aspire.categoryservice.service.CategoryService;
import com.aspire.categoryservice.service.ExcelService;
import com.aspire.categoryservice.service.dto.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private final CategoryService categoryService;

    @Override
    public ByteArrayOutputStream generateEncryptedCategorys(List<CategoryResponseDTO> categorys, String password) throws IOException, InvalidFormatException, GeneralSecurityException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet  ("Categories");
        String[] headers = {"slNo", "categoryId","categoryName","categoryDescription", "categoryCode"};
        Row headerRow = sheet.createRow(0);
        for(int index = 0; index < headers.length; index++) {
            Cell cell = headerRow.createCell(index);
            cell.setCellValue(headers[index]);

        }
        int sno = 0;
        for(CategoryResponseDTO category : categorys) {
            sno++;
            Row row = sheet.createRow(sno);
            row.createCell(0).setCellValue(sno);
            row.createCell(1).setCellValue(category.getCategoryId());
            row.createCell(2).setCellValue(category.getCategoryName());
            row.createCell(3).setCellValue(category.getCategoryDescription());
            row.createCell(4).setCellValue(category.getCategoryCode());
        }
        ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
        workbook.write(tempOut);
        workbook.close();
        POIFSFileSystem fs=new POIFSFileSystem();
        EncryptionInfo info=new EncryptionInfo(EncryptionMode.agile);
        Encryptor encryptor = info.getEncryptor();
        encryptor.confirmPassword(password);
        try (OPCPackage opc = OPCPackage.open(new ByteArrayInputStream(tempOut.toByteArray())); OutputStream encryptedOutput = encryptor.getDataStream(fs)) {
            opc.save(encryptedOutput);

        }
        ByteArrayOutputStream finalOut = new ByteArrayOutputStream();
        fs.writeFilesystem(finalOut);

        return finalOut;
    }
}


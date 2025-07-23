package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.employeeCreation;

import java.io.*;
import java.util.*;

public class readerExcel {
    private static final Logger logger = LoggerFactory.getLogger(readerExcel.class);


    public static List<Map<String, String>> readExcelData(String filePath) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                String header = headerRow.getCell(j).getStringCellValue();
                String cellValue = getCellValue(row.getCell(j));
                rowData.put(header, cellValue);
            }
            data.add(rowData);
        }

        workbook.close();
        fis.close();
        return data;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}

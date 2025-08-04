package excelReader;

import io.cucumber.java.Scenario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class dataToExcel {

    public static void updateExcelStatus(String filePath, Scenario scenario) {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/" + filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Use first sheet
            if (sheet == null) return;

            // Find column indexes dynamically
            Map<String, Integer> columnMap = new HashMap<>();
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) return;

            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                Cell cell = headerRow.getCell(j);
                if (cell != null) {
                    columnMap.put(cell.getStringCellValue().toLowerCase().trim(), j);
                }
            }

            Integer descCol = columnMap.get("description");
            Integer statusCol = columnMap.get("status");
            Integer remarksCol = columnMap.get("remarks");

            if (descCol == null || statusCol == null || remarksCol == null) return;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell descCell = row.getCell(descCol);
                if (descCell != null && descCell.toString().trim().equalsIgnoreCase(scenario.getName().trim())) {
                    row.createCell(statusCol).setCellValue(scenario.isFailed() ? "Fail" : "Pass");
                    row.createCell(remarksCol).setCellValue(scenario.isFailed() ? "Scenario failed invalid data or elemet not found" : "Scenario passed");
                    break;
                }
            }

            try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/" + filePath)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

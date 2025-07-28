package excelReader;

import POM.dashboardPage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class dataFromExcel {
    private static final Logger logger = LoggerFactory.getLogger(dataFromExcel.class);
    public static void updateStatus(String filePath, String sheetName, String testCaseId, String status, String remarks) {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/" + filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int statusColIndex = -1;
            int remarksColIndex = -1;

            Row headerRow = sheet.getRow(0);
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                String cellValue = headerRow.getCell(j).getStringCellValue().toLowerCase();
                if (cellValue.contains("status")) statusColIndex = j;
                if (cellValue.contains("remarks")) remarksColIndex = j;
            }
            if (statusColIndex == -1 || remarksColIndex == -1) {
                logger.info("Status or Remarks column not found.");
                return;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell idCell = row.getCell(1); // Assuming TestCaseID is in 2nd column
                if (idCell != null && idCell.toString().equalsIgnoreCase(testCaseId)) {
                    row.createCell(statusColIndex).setCellValue(status);
                    row.createCell(remarksColIndex).setCellValue(remarks);
                    break;
                }
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

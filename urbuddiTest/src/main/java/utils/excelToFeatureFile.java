package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class excelToFeatureFile {

    public static void main(String[] args) {
        String excelFilePath = "urbuddiTest/testdata/testdata.xlsx";
        String featureFilePath = "src/test/resources/features/login.feature";
        String sheetName = "Sheet1";

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis);
             BufferedWriter writer = new BufferedWriter(new FileWriter(featureFilePath))) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

            Row headerRow = sheet.getRow(0);
            int usernameCol = -1, passwordCol = -1;

            for (Cell cell : headerRow) {
                String colName = getCellValue(cell).toLowerCase().trim();
                if (colName.equals("username")) usernameCol = cell.getColumnIndex();
                if (colName.equals("password")) passwordCol = cell.getColumnIndex();
            }

            if (usernameCol == -1 || passwordCol == -1)
                throw new RuntimeException("Missing 'username' or 'password' headers in Excel");

            // Write fixed Gherkin structure
            writer.write("Feature: Login to urbuddi via scenario outline\n\n");
            writer.write("  Scenario Outline: Login with multiple credentials\n");
            writer.write("    Given Urbuddi is launched \"https://dev.urbuddi.com/login\"\n");
            writer.write("    When I login with \"<username>\" and \"<password>\"\n");
            writer.write("    Then user login successfully\n\n");
            writer.write("    Examples:\n");
            writer.write("      | username | password |\n");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String username = getCellValue(row.getCell(usernameCol));
                String password = getCellValue(row.getCell(passwordCol));

                if (!username.isEmpty() && !password.isEmpty()) {
                    writer.write(String.format("      | %s | %s |\n", username, password));
                }
            }

            System.out.println(" Feature file generated at: " + featureFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // optional: format as date
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}


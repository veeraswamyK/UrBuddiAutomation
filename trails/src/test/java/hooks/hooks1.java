package hooks;

import excelReader.dataToExcel;
import excelReader.emailSender;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.screenshotGenerator;
import utils.testRail;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static utils.testRailExcel.getLatestExcelFilePath;

public class hooks1 {
    private static final Logger logger = LoggerFactory.getLogger(hooks1.class);
    private static final int projectId = 1;
    private static final int runId = 9;
    public static WebDriver driver;
    private static Map<String, Integer> caseMap;
    static {
        try {
            caseMap = testRail.getTitleAndIdToMap(projectId);
        } catch (IOException e) {
            logger.error("Failed to load case map from TestRail", e);
        }
    }
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    @Before
    public void setup() {
        getDriver();
    }
    @After
    public void teardown(Scenario scenario) {
        String scenarioTitle = scenario.getName().trim();

        if (scenario.isFailed() && driver != null) {
            screenshotGenerator.takeScreenshot(driver);
        }

        //String excelPath = "Book.xlsx";
        //dataToExcel.updateExcelStatus(excelPath, scenario);

        try {
            if (caseMap.containsKey(scenarioTitle)) {
                int caseId = caseMap.get(scenarioTitle);
                int statusId = scenario.isFailed() ? 5 : 1;
                String comment = scenario.isFailed() ? "Scenario Failed" : "Scenario Passed";

                testRail.updateTestCaseStatus(runId, caseId, statusId, comment);
                logger.info("TestRail updated for scenario: " + scenarioTitle);
            } else {
                logger.warn("⚠No matching TestRail case found for: " + scenarioTitle);
            }
        } catch (Exception e) {
            logger.error("Failed to update TestRail for scenario: " + scenarioTitle, e);
        }

        quitDriver();
    }
    @AfterAll
    public static void sendEmailAfterAll() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
                "\"C:\\Program Files (x86)\\allure\\allure-2.34.1\\bin\\allure.bat\" generate target/allure-results -o target/allure-report --clean");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        process.waitFor();

        logger.info("email sending ......");
        String subject = "Automation Test Report on urBuddi WebApplication - " + LocalDate.now();
        String body = "All tests are executed.\n Please find the attachments for the reports.\n 1. local excel\n 2. allure reports\n 3. TestRailreports";
        String excelPath = System.getProperty("user.dir") + "/Book.xlsx";
        String allureHtml = System.getProperty("user.dir") + "/target/allure-report/index.html";
        String downloadFolder = "C:\\Users\\veera\\Downloads";
        String latestExcelPath = getLatestExcelFilePath(downloadFolder);

        if (latestExcelPath != null) {
            System.out.println("Latest Excel File: " + latestExcelPath);
        } else {
            System.out.println(" No Excel file found.");
        }

        emailSender.sendReport(subject, body, "veeraswamy.kalluri@optimworks.com", excelPath, allureHtml,latestExcelPath);
        logger.info("email sent ......");
    }
}

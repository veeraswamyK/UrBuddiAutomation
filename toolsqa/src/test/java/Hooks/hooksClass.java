package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.TestRailExcelSender;
import utility.TestRailIntegration;
import utility.emailsender;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static utility.TestRailExcelSender.getLatestExcelFilePath;

public class hooksClass {
    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(hooksClass.class);

    private static final int projectId = 1;
    private static final int runId = 1;
    private static Map<String, Integer> caseMap;
    static {
        try {
            caseMap = TestRailIntegration.getTitleAndIdToMap(projectId);
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
    public void end(Scenario scenario) {
        String scenarioTitle = scenario.getName().trim();
        try {
        if (caseMap.containsKey(scenarioTitle)) {
            int caseId = caseMap.get(scenarioTitle);
            int statusId = scenario.isFailed() ? 5 : 1;
            String comment = scenario.isFailed() ? "Scenario Failed" : "Scenario Passed";

            TestRailIntegration.updateTestCaseStatus(runId, caseId, statusId, comment);
            logger.info("TestRail updated for scenario: " + scenarioTitle);
        } else {
            logger.warn("⚠No matching TestRail case found for: " + scenarioTitle);
        }
    } catch (Exception e) {
        logger.error("Failed to update TestRail for scenario: " + scenarioTitle, e);
    }

        quitDriver();
        if (driver!=null)
        {
            driver.quit();
        }
    }
    @AfterAll
    public static void sendEmailAfterAll() throws IOException, InterruptedException {
        WebDriver reportDriver = new ChromeDriver(); // fresh driver for download
        reportDriver.manage().window().maximize();

        TestRailExcelSender sender = new TestRailExcelSender(reportDriver);
        sender.testRailExcelToMail();  // exports Excel file
        logger.info("email sending ......");
        String subject = "Automation Test Report on urBuddi WebApplication - " + LocalDate.now();
        String body = "All tests are executed.\n Please find the attachments for the reports.\n 1.single scenario adding status to the test rail";
        //String excelPath = System.getProperty("user.dir") + "/Book.xlsx";
        //String allureHtml = System.getProperty("user.dir") + "/target/allure-report/index.html";
        String downloadFolder = "C:\\Users\\veera\\Downloads";

        String latestExcelPath = getLatestExcelFilePath(downloadFolder);

        if (latestExcelPath != null) {
            System.out.println("Latest Excel File: " + latestExcelPath);
        } else {
            System.out.println(" No Excel file found.");
        }

        emailsender.sendReport(subject, body, "veeraswamy.kalluri@optimworks.com", latestExcelPath);
        logger.info("email sent ......");
    }


}

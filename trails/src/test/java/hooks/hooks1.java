package hooks;

import POM.dashboardPage;
import excelReader.emailSender;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class hooks1 {
    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(hooks1.class);

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
    public void teardown() {
        quitDriver();
    }

    @AfterAll
    public static void sendEmailAfterAll() {
        logger.info("email sending ......");
        String subject = "Automation Test Report - " + LocalDate.now();
        String body = "All tests executed.\nCheck detailed report at: ./target/cucumber-report.html";
        emailSender.sendReport(subject, body, "veeraswamy.kalluri@optimworks.com");
    }
}


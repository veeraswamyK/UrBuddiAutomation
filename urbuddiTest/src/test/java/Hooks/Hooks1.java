package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.driverManager;
import utils.screenshotGenerator;

import java.time.Duration;

public class Hooks1 {


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverManager.setDriver(driver);


    }
    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = driverManager.getDriver();
            screenshotGenerator.takeScreenshot(driver);
        }
    }
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver=driverManager.getDriver();
        if(scenario.isFailed() && driver!=null){
            screenshotGenerator.takeScreenshot(driver);
        }
        driverManager.quitDriver();
    }
}

package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class hooksClass {
    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(hooksClass.class);
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
    public void end()
    {
        if (driver!=null)
        {
            driver.quit();
        }
    }
}

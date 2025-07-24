package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class hooks1 {
    public static WebDriver driver;
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
    }


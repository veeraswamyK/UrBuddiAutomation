package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

    public class Hooks1 {
        public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

        public static WebDriver getDriver()
        {
            return driver.get();
        }

        public static void setDriver(WebDriver driverInstance) {
            driver.set(driverInstance);
        }

        public static void quitDriver() {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
        }

        @Before
        public void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-allow-origins=*");
            String os = System.getProperty("os.name").toLowerCase();

            Map<String, Object> prefs = new HashMap<>();


            prefs.put("profile.default_content_setting_values.notifications", 2);

            options.setExperimentalOption("prefs", prefs);



            if (os.contains("linux")) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Hooks.Hooks1.setDriver(driver);
        }

        @After
        public void tearDown(Scenario scenario) {
            WebDriver driver = Hooks.Hooks1.getDriver();
            Hooks.Hooks1.quitDriver();
        }
    }



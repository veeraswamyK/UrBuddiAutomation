package steps;

import Hooks.Hooks1;
import POM.dashboardPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import POM.loginPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.NoSuchElementException;

public class loginSteps {
    private static final Logger logger = LoggerFactory.getLogger(loginSteps.class);

    WebDriver driver = Hooks1.getDriver();
    loginPage loginPage;
    dashboardPage Dashboard;

    @Given("Urbuddi is launched {string}")
    public void urbuddi_is_launched(String url) {
        driver.get(url);
        loginPage = new loginPage(driver);
        logger.info("opening the urbuddi application");
    }

    @When("I login with {string} and {string}")
    public void iLoginWithAnd(String arg0, String arg1) {

        loginPage.EmailField().sendKeys(arg0);
        loginPage.PasswordField().sendKeys(arg1);
        loginPage.LoginButton().click();
        logger.info("logging in with multiple credentials");
    }

    @And("User login successfully")
    public void userLoginSuccessfully() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String a = "//p[contains(text(),'Dashboard')]";
            WebElement dashboardDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(a)));

            if (dashboardDiv.isDisplayed()) {
                Assert.assertEquals(dashboardDiv.getText(), "Dashboard");
                logger.info("logged in completed");
            } else {
                logger.info("logging in failed");
            }
        } catch (TimeoutException e) {
            Assert.fail("unable to login with invalid credentials");
            logger.info("logging in failed");
        } catch (NoSuchElementException e) {
            Assert.fail("unable to login with invalid credentials may be element not found");
            logger.info("logging in failed");
        }
    }
}



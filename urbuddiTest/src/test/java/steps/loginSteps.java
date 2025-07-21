package steps;

import POM.dashboardPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import POM.loginPage;

import org.testng.Assert;
import utils.driverManager;
import utils.readerExcel;
import utils.screenshotGenerator;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class loginSteps {
    WebDriver driver = driverManager.getDriver();
    loginPage loginPage;
    dashboardPage Dashboard;

    @Given("Urbuddi is launched {string}")
    public void urbuddi_is_launched(String url) {
        driver.get(url);
        loginPage=new loginPage(driver);
    }

    @When("I login with {string} and {string}")
    public void iLoginWithAnd(String arg0, String arg1) {

        loginPage.EmailField().sendKeys(arg0);
        loginPage.PasswordField().sendKeys(arg1);
        loginPage.LoginButton().click();


    }

        @Then("user login successfully")
        public void userLoginSuccessfully()
        {
            screenshotGenerator.takeScreenshot(driver);
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                String a = "//p[contains(text(),'Dashboard')]";
                WebElement dashboardDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(a)));

                if (dashboardDiv.isDisplayed()) {
                    Assert.assertEquals(dashboardDiv.getText(),"Dashboard");
                    System.out.println("Login successful! Dashboard is displayed");
                } else {
                    System.out.println("Dashboard is not displayed!");
                }
            } catch (TimeoutException e) {
                screenshotGenerator.takeScreenshot(driver);
               System.out.println("Dashboard not found  Login  failed.");
                screenshotGenerator.takeScreenshot(driver);
               Assert.fail("unable to login with invalid credentials");
               screenshotGenerator.takeScreenshot(driver);
            } catch (NoSuchElementException e) {
                System.out.println("Dashboard element not found");
            }

        }


        }



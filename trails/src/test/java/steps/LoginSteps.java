package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import hooks.hooks1;
import POM.login;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginSteps {

    WebDriver driver = hooks1.getDriver() ;

    login loginpage=new login(driver);

    @Given("Flipkart homepage is launched")
    public void flipkart_homepage_is_launched() {

        driver.get("https://dev.urbuddi.com/login");
    }


    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String a = "//p[contains(text(),'Dashboard')]";
            WebElement dashboardDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(a)));

            if (dashboardDiv.isDisplayed()) {
                Assert.assertEquals(dashboardDiv.getText(), "Dashboard");
            }
        }catch (TimeoutException e){
            System.out.println("failed");
        }
    }
    @When("User enters valid {string} and {string}")
    public void userEntersValidAndPassword(String arg0, String arg1) throws Throwable
    {
        loginpage.EmailField().sendKeys(arg0);
        System.out.println("sent"+arg0);
        loginpage.PasswordField().sendKeys(arg1);
        System.out.println("sent"+arg1);
        loginpage.LoginButton().click();
    }
}

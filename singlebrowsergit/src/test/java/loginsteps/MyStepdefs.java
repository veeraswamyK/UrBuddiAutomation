package loginsteps;

import Hooks.Hooks1;
import POM.loginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

public class MyStepdefs {
    WebDriver driver = Hooks1;


    loginPage loginPage;
        @Given("Urbuddi is launched {string}")
        public void applaumched (String url)
        {
            driver.get(url);

        }
        @When("I login with {string} and {string}")
        public void logginf(String arg0, String arg1) {
            loginPage.EmailField().sendKeys(arg0);
            loginPage.PasswordField().sendKeys(arg1);
            loginPage.LoginButton().click();
        }
        @Then("User login successfully")
        public void success()
        {

        }
    }


package steps;

import POM.dashboardPage;
import POM.employeePage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import POM.loginPage;

import utils.driverManager;
import utils.readerExcel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;


public class employeeCreation

    {
        WebDriver driver = driverManager.getDriver();
        loginPage loginPage;
        dashboardPage dashboardPage;
        employeePage employeePage;
        @Given("Urbuddi is launched throuh the url {string}")
        public void urbuddiIsLaunchedThrouhTheUrl(String arg0)
        {
            driver.get(arg0);
            loginPage=new loginPage(driver);
        }
        @When("user login  using the user name  {string} and {string}")
        public void userLoginUsingTheUserNameAnd(String arg0, String arg1) {


                loginPage.EmailField().clear();

                loginPage.EmailField().sendKeys(arg0);
                loginPage.PasswordField().clear();
                loginPage.PasswordField().sendKeys(arg1);
                WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
                //loginPage.LoginButton().click();
                loginPage.LoginButton().click();



        }
        @Given("User is already logged in")
        public void userAlreadyLoggedin()
        {
            dashboardPage = new dashboardPage(driver);
            dashboardPage.onDashboard();
        }
        @When("user click on {string} in dashboard")
        public void addingNewEmployee(String a)
        {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    dashboardPage.goToEmployeePage(a);
        }
        @When("user click on {string}")
        public void addingNewEmployeeButton(String b)
        {
            employeePage=new employeePage(driver);
            employeePage.to_Add_employee(b);
        }
        @When("fill the {string} details and click on add")
        public void addingAllDetails(String a)
        {
            employeePage.reqiredDetails(a);
        }
        @Then("user able to save Employee successfully")
        public void createdEmployeeSuccessfully()
        {
            employeePage.clickOnAddAfterFilling();

        }


    }



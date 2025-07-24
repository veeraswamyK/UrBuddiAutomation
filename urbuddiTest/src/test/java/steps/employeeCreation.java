package steps;

import Hooks.Hooks1;
import POM.dashboardPage;
import POM.employeePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import POM.loginPage;
import org.slf4j.Logger;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class employeeCreation {
    private static final Logger logger = LoggerFactory.getLogger(employeeCreation.class);
    WebDriver driver = Hooks1.getDriver();
    loginPage loginPage;
    dashboardPage dashboardPage;
    employeePage employeePage;

    @Given("Urbuddi is launched throuh the url {string}")
    public void urbuddiIsLaunchedThrouhTheUrl(String arg0) {
        driver.get(arg0);
        loginPage = new loginPage(driver);
        logger.info("app opening");

    }

    @When("User login  using the user name  {string} and {string}")
    public void userLoginUsingTheUserNameAnd(String arg0, String arg1) {
        loginPage.EmailField().clear();
        loginPage.EmailField().sendKeys(arg0);
        loginPage.PasswordField().clear();
        loginPage.PasswordField().sendKeys(arg1);
        loginPage.LoginButton().click();
        logger.info("entering details to login");

    }

    @Given("User is already logged in")
    public void userAlreadyLoggedin() {
        dashboardPage = new dashboardPage(driver);
        dashboardPage.onDashboard();
        logger.info("user logged in");
    }

    @When("User click on {string} in dashboard")
    public void addingNewEmployee(String a) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        dashboardPage.goToEmployeePage(a);
        logger.info("adding new employee");

    }

    @And("User click on {string}")
    public void addingNewEmployeeButton(String b) {
        employeePage = new employeePage(driver);
        employeePage.to_Add_employee(b);
        logger.info("Add employee");
    }

    @And("Fill the {string} details and click on add")
    public void addingAllDetails(String a) {
        employeePage.reqiredDetails(a);
        logger.info("entering details ");
    }

    @Then("User able to save Employee successfully")
    public void createdEmployeeSuccessfully() {
        employeePage.clickOnAddAfterFilling();
        logger.info("clicking on add button");
    }
}



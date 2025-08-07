package steps;

import POM.dashboardPage;
import POM.employeePage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import hooks.hooks1;
import POM.loginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class stepDefenitions {
    WebDriver driver = hooks1.getDriver();
    loginPage loginpage = new loginPage(driver);
    dashboardPage dashboard = new dashboardPage(driver);
    employeePage employeeCreation = new employeePage(driver);

    private static final Logger logger = LoggerFactory.getLogger(stepDefenitions.class);

    @Given("urbuddi loginpage is launched")
    public void urbuddiLoginpageIsLaunched() {
        driver.get("https://dev.urbuddi.com/login");
    }


    @When("User enters valid data from {string}and{string}")
    public void userEntersValidDataFromAnd(String arg0, String arg1) {
        loginpage.EmailField().clear();
        loginpage.EmailField().sendKeys(arg0);
        logger.info("Logging in with: " + arg0);
        loginpage.PasswordField().clear();
        loginpage.PasswordField().sendKeys(arg1);
        loginpage.LoginButton().click();
        logger.info("Logging in with: " + arg0 + " | " + arg1);
//        loginpage.EmailField().sendKeys(arg0);
//        System.out.println("sent" + arg0);
//        loginpage.PasswordField().sendKeys(arg1);
//        System.out.println("sent" + arg1);
//        loginpage.LoginButton().click();
    }


    @Then("User should enter the status of the test case")
    public void userShouldEnterTheStatusOfTheTestCase() {
       logger.info("data is updating to excel");
    }

    @And("Click on logout on dashboard and yes on prompt")
    public void clickOnLogoutOnDashboard() {
        dashboard.logout();
    }


    @Then("urbuddi loginpage is displayed again for login")
    public void urbuddiLoginpageIsDisplayedAgainForLogin() {
        //Assert.assertEquals();
    }

    @And("User click on {string} in dashboard")
    public void userClickOnInDashboard(String arg0) {

        dashboard.goToEmployeePage(arg0);
        logger.info("adding new employee");
    }

    @And("User click on {string}")
    public void userClickOn(String arg0) {
        employeeCreation.to_Add_employee(arg0);
        logger.info("Add employee");
    }

    @And("Fill the {string} details and click on add")
    public void fillTheDetailsAndClickOnAdd(String arg0) {
        employeeCreation.reqiredDetails(arg0);
    }

    @Then("User able to save Employee successfully")
    public void userAbleToSaveEmployeeSuccessfully() {
        employeeCreation.clickOnAddAfterFilling();
    }
}







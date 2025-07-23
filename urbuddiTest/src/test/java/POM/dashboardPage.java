package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitHelper;

import java.time.Duration;

public class dashboardPage {
    private static final Logger logger = LoggerFactory.getLogger(dashboardPage.class);
    WebDriver driver;
 private  WaitHelper waitHelper;
    public dashboardPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper=new WaitHelper(driver);

    }

    public void onDashboard() {

        By dashBoard = By.xpath("//p[contains(text(),'Dashboard')]/parent::div");
        WebElement dashBoardElement = waitHelper.waitForVisibility(dashBoard, 10);
        dashBoardElement.click();
        logger.info("on the dash board");
    }

    public void goToEmployeePage(String a) {

        By employeeClick = By.xpath("//p[normalize-space(text()) = '" + a + "']");
        WebElement employeeClickElement=waitHelper.waitForClickability(employeeClick, 10);
        employeeClickElement.click();
        logger.info("on the employee creation");


    }

    public void goToLeaveManagement(String a) {


    }

}

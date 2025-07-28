package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.waitHelper;

import java.time.Duration;

public class dashboardPage {
    private static final Logger logger = LoggerFactory.getLogger(dashboardPage.class);
    WebDriver driver;
    private waitHelper WaitHelper;

    public dashboardPage(WebDriver driver) {
        this.driver = driver;
        this.WaitHelper = new waitHelper(driver);

    }

    public void onDashboard() {

        By dashBoard = By.xpath("//p[contains(text(),'Dashboard')]/parent::div");
        WebElement dashBoardElement = WaitHelper.waitForVisibility(dashBoard, 10);
        dashBoardElement.click();
        logger.info("on the dash board");
    }

    public void goToEmployeePage(String a) {

        By employeeClick = By.xpath("//p[normalize-space(text()) = '" + a + "']");
        WebElement employeeClickElement = WaitHelper.waitForClickability(employeeClick, 10);
        employeeClickElement.click();
        logger.info("on the employee creation");


    }

    public void logout()
    {
        By logoutElement = By.cssSelector("p.logout-btn-nav");
        WebElement logoutElementClick = WaitHelper.waitForClickability(logoutElement, 10);
        logoutElementClick.click();
        logger.info("clicked on logout");
        By clickYesToLogout=By.xpath("//button[text()='Yes']");
        WebElement clickYesToLogoutFromUrbuddi = WaitHelper.waitForClickability(clickYesToLogout, 10);
        clickYesToLogoutFromUrbuddi.click();
        logger.info("clicked on logout yes");
    }

}

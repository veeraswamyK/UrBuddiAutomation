package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class dashboardPage {
    WebDriver driver;
    public dashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    public void onDashboard()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboradDiv= driver.findElement(By.xpath("//p[contains(text(),'Dashboard')]/parent::div"));
        wait.until(ExpectedConditions.visibilityOf(dashboradDiv));
        System.out.println("selected page is dashbord");
    }
    public void goToEmployeePage(String a)
    {
        var element= driver.findElement(By.xpath("//p[normalize-space(text()) = '"+a+"']"));
        element.click();
        System.out.println("employee clicked");

    }
    public void goToLeaveManagement(String a)
    {
        var element= driver.findElement(By.xpath("//p[normalize-space(text()) = '"+a+"']"));
        element.click();
        System.out.println("employee clicked");

    }

}

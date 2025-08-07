package POM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class homePage {
    WebDriver driver;
    WebDriverWait wait;

    public homePage(WebDriver driver)
    {
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void tutorialEnroll()
    {
        WebElement tutorialElement = driver.findElement(By.xpath("//span[text()='Tutorials']"));
        wait.until(ExpectedConditions.elementToBeClickable(tutorialElement));
        tutorialElement.click();
    }
    public void selectTechnology(String techName) {

        WebElement techElement = driver.findElement(By.xpath("//span[text()='" + techName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(techElement));
        techElement.click();
    }

    public void selectTool(String toolName)
    {

        WebElement toolElement = driver.findElement(By.xpath("//ul[@class='active']//a[text()='" + toolName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(toolElement));
        toolElement.click();
    }
}

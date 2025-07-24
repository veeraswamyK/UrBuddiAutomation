package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class loginPage {

    WebDriver driver;

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Web elements as methods (best practice for lazy loading)
    public WebElement EmailField() {
        String a = "//input[@type='email']";
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(a)));
        return driver.findElement(By.xpath(a));

    }

    public WebElement PasswordField() {
        String a = "//input[@type='password']";
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(a)));
        return driver.findElement(By.xpath(a));
    }

    public WebElement LoginButton() {
        String a = "//button[contains(text(),'Login')]";
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(a)));
        return driver.findElement(By.xpath(a));
    }

}

package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class actions {
    WebDriver driver;
    commonLocators locators;
    public actions(WebDriver driver){
        this.driver=driver;
        this.locators = new commonLocators(driver);

    }
public void clickElement(String a)
{
    By locator = locators.goToSelectedElement(a);
    driver.findElement(locator).click();
}
}

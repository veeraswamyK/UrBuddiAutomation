package POM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class homePage {
    WebDriver driver;

    @FindBy(xpath="//span[text()='Tutorials']")
    WebElement tutorial;
    @FindBy(xpath="//span[text()='Tutorials']")
    WebElement tool;

    public homePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void tutorialEnroll()
    {
        tutorial.click();
    }
    public void selectTechnology(String techName) {
        WebElement techElement = driver.findElement(By.xpath("//span[text()='" + techName + "']"));
        techElement.click();
    }

    public void selectTool()
    {
        tool.click();
    }
}

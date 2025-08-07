package StepDefenitions;

import POM.homePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.DataTable;
import org.openqa.selenium.WebDriver;
import Hooks.hooksClass;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class steps {
    WebDriver driver;
    homePage homepage;
    public steps(){
        this.driver= hooksClass.getDriver();
        homepage = new homePage(driver);

    }

    @Given("ToolsQa Application {string} is launched and user is on home page")
    public void toolsqaApplicationIsLaunchedAndUserIsOnHomePageIsDisplayed(String url)
    {

        driver.get(url);
        homepage.tutorialEnroll();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @When("User click on user tutorials and selects the technology wanted to learn {string}")
    public void userClickOnUserTutorialsAndSelectsTheTechnologyWantedToLearn(String arg0) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            homepage.selectTechnology(arg0);  // You should modify homepage to handle this
    }

    @And("User selects the tool to learn {string}")
    public void userSelectsTheToolToLearn(String arg0)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homepage.selectTool(arg0);
    }

    @Then("Selected tool tutorial is opened")
    public void selectedToolTutorialIsOpened()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("COMPLETED ONE FLOW");
    }
}

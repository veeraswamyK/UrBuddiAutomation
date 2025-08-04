package StepDefenitions;

import POM.homePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.DataTable;
import org.openqa.selenium.WebDriver;
import Hooks.hooksClass;

import java.util.List;

public class steps {
    WebDriver driver;
    homePage homepage = new homePage(driver);
    public steps(){
        this.driver= hooksClass.getDriver();

    }
    @Given("ToolsQa Application {string} is launched and user is on home page")
    public void toolsqaApplicationIsLaunchedAndUserIsOnHomePageIsDisplayed(String url)
    {
        driver.get(url);
    }

    @When("User click on user tutorials and selects the technology wanted to learn:")
    public void userClickOnUserAndSelectsTheWantedToLearn(DataTable dataTable)
    {
        homepage.tutorialEnroll();
        List<String> technologies = dataTable.asList(String.class);

        for (String tech : technologies) {
            homepage.selectTechnology(tech);  // You should modify homepage to handle this
        }
    }

    @And("User selects the tool to learn:")
    public void userSelectsTheToolToLearn()
    {
        homepage.selectTool();
    }

    @Then("Selected tool tutorial is opened")
    public void selectedToolTutorialIsOpened()
    {

    }


}

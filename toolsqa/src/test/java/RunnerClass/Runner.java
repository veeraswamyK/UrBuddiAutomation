package RunnerClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/toolsqa.feature", // path to your .feature files
        glue = {"StepDefenitions", "Hooks"}, // package(s) containing step defs & hook
        monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {
}

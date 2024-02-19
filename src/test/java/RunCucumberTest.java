import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"classpath:features"},
        glue = {"com.alfred.common", "com.alfred.pageobjects", "com.alfred.stepdefinitions", "com.alfred.utils"},
        plugin = {
            "pretty",
            "html:target/cucumber-reports/cucumber.html",
            "json:target/cucumber-reports/cucumber.json",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
    )

public class RunCucumberTest {
}
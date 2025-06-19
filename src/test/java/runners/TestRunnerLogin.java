package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/Login",
        },
        glue = {"stepDefinitions", "hooks"},
        tags = "@login",
        plugin = {
                "pretty",
                "html:target/reports/login-report.html"
        },
        monochrome = true
)
public class TestRunnerLogin {}


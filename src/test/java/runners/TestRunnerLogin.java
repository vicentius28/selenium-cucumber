package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/Login",
                "src/test/resources/features/Logout"
        },
        glue = {"stepDefinitions", "hooks"},
        tags = "@login or @logout",
        plugin = {
                "pretty",
                "html:target/reports/login-logout-report.html"
        },
        monochrome = true
)
public class TestRunnerLogin {}


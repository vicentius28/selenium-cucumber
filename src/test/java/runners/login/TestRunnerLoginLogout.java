package runners.login;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/LoginLogout.feature",
        glue = {"stepDefinitions", "hooks"},
        tags = "@login_logout",
        plugin = {"pretty", "html:target/report-login-logout.html"},
        monochrome = true
)
public class TestRunnerLoginLogout {}

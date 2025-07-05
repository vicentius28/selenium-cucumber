package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Delete",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/reports/Delete-report.html"},
        monochrome = true

)
public class TestRunnerDelete {}

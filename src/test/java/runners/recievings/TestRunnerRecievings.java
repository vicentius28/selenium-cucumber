package runners.recievings;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Recievings",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/reports/recievings-report.html"},
        monochrome = true,
        tags = "@ingresar_recepcion"
)

public class TestRunnerRecievings {
}

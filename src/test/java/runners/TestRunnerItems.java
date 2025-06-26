package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Items",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/reports/items-report.html"},
        monochrome = true,
        tags = "@item_registrado"
)
public class TestRunnerItems {}

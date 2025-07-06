package runners.suppliers;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Suppliers",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/reports/suppliers-report.html"},
        monochrome = true
)
public class TestRunnerSuppliers {}

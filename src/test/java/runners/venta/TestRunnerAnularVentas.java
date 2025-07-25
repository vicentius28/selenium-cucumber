package runners.venta;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Ventas",
        glue = {"stepDefinitions", "hooks"},
        tags = "@anular_ventas",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ventas-report.html"
        },
        monochrome = true
)
public class TestRunnerAnularVentas {}

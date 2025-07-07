package runners.venta;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/flows/SalesAnnularFlow.feature",
        glue = {"stepDefinitions", "hooks"},
        tags = "@crear_anular_ventas",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ventas-report.html"
        },
        monochrome = true
)
public class TestRunnerSalesFlow {}

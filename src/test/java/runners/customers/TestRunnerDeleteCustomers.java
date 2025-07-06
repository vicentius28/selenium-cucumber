package runners.customers;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Customers",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/reports/customers-report.html"},
        monochrome = true,
        tags = "@cliente_eliminado"

)
public class TestRunnerDeleteCustomers {}

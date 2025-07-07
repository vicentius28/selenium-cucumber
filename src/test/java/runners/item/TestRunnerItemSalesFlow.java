package runners.item;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features =  {
                "src/test/resources/features/flows/ItemSalesFlow.feature",
                "src/test/resources/features/Customers"
        },
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        tags = "@cliente_registrado or @flujo_item_sales",
        monochrome = true
)
public class TestRunnerItemSalesFlow {
}

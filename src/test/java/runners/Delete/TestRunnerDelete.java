package runners.Delete;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import runners.customers.TestRunnerDeleteCustomers;
import runners.item.TestRunnerDeleteItems;
import runners.suppliers.TestRunnerDeleteSuppliers;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/delete-report.html",
                "json:target/cucumber-reports/delete-report.json"
        },
        monochrome = true
)

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestRunnerDeleteCustomers.class,
        TestRunnerDeleteSuppliers.class,
        TestRunnerDeleteItems.class,
})
public class TestRunnerDelete {

}

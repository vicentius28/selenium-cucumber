package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import io.cucumber.junit.CucumberOptions;



@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json"
        },
        monochrome = true
)

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestRunnerCustomers.class,
        TestRunnerSuppliers.class,
        TestRunnerItems.class,
        TestRunnerVentas.class,
        TestRunnerDelete.class

})
public class TestRunnerAll {

}

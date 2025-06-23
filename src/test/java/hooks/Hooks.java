package hooks;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("========== INICIANDO ESCENARIO ==========");
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        DriverFactory.quitDriver();
        System.out.println("========== FINALIZANDO ESCENARIO ==========\n");
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

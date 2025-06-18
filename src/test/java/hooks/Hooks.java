package hooks;

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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("❌ ESCENARIO FALLÓ: " + scenario.getName());
            // Adjunta screenshot si falla
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        } else {
            System.out.println("✅ ESCENARIO COMPLETADO: " + scenario.getName());
        }

        if (driver != null) {
            driver.quit();
        }

        System.out.println("========== FINALIZANDO ESCENARIO ==========\n");
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

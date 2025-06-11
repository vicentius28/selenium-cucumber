package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        // Asegúrate de tener chromedriver en tu PATH o especifica su ubicación aquí
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (driver != null) {
            if (scenario.getSourceTagNames().contains("@logout")) {
                try {
                    // Solo hacer logout si está visible
                    if (!driver.findElements(By.linkText("Logout")).isEmpty()) {
                        driver.findElement(By.linkText("Logout")).click();
                        System.out.println("Logout ejecutado correctamente.");
                    } else {
                        System.out.println("Logout no disponible, posiblemente ya fue hecho.");
                    }
                } catch (Exception e) {
                    System.out.println("Error al intentar cerrar sesión: " + e.getMessage());
                }
            }
            driver.quit();
        }
    }


    public static WebDriver getDriver() {
        return driver;
    }



}

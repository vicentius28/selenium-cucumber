package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class VentasPage {
    WebDriver driver;

    public VentasPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean loadVentas() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Espera hasta que el módulo de inicio esté visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("home_module_list")));

            // Luego espera a que el módulo de ventas (div[7]) esté presente y clickeable
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='home_module_list']/div[7]"))).click();

            return true;
        } catch (Exception e) {
            System.out.println("No se pudo cargar el módulo de ventas: " + e.getMessage());
            return false;
        }
    }
}

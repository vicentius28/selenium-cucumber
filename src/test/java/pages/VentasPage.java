package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VentasPage {
    private final WebDriver driver;


    // Element locators en CamelCase
    private final By itemInput = By.id("item");
    private final By salesNavItem = By.xpath("//*[@id='home_module_list']/div[7]");
    private final By quantityInput = By.xpath("//*[@id='cart_contents']/tr[1]/td[5]/input");
    private final By agregarPagoButton = By.id("add_payment_button");
    private final By finalizarPagoButton = By.id("finish_sale_button");
    private final By codigoBarraBoleta = By.xpath("//*[@id='barcode']/img");
    private final By tipoPagoDropdown = By.cssSelector("button[data-id='payment_types']");

    public VentasPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean cargarModuloVentas() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("home_module_list")));
            wait.until(ExpectedConditions.elementToBeClickable(salesNavItem)).click();
            return true;
        } catch (Exception e) {
            System.out.println("No se pudo cargar el módulo de ventas: " + e.getMessage());
            return false;
        }
    }

    public boolean agregarItemAVenta(String nombreProducto, String cantidadStr) {
        try {
            int cantidad = Integer.parseInt(cantidadStr);  // Validar cantidad
            WebElement input = driver.findElement(itemInput);
            input.clear();
            input.sendKeys(nombreProducto);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[1]/li")));
            input.sendKeys(Keys.DOWN, Keys.RETURN);

            WebElement cantidadInputEl = driver.findElement(quantityInput);
            cantidadInputEl.sendKeys(Keys.CONTROL + "a");
            cantidadInputEl.sendKeys(Keys.DELETE);
            cantidadInputEl.sendKeys(String.valueOf(cantidad));
            cantidadInputEl.sendKeys(Keys.ENTER);

            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.print("Error al agregar item con cantidad: " + e.getMessage());
            return false;
        }
    }


    public boolean realizarPago(String tipoPago) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By opcionPago = By.xpath("//div[@class='dropdown-menu open']//span[contains(text(), '" + tipoPago + "')]");
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(tipoPagoDropdown));
            dropdown.click();

            WebElement opcion = wait.until(ExpectedConditions.elementToBeClickable(opcionPago));
            opcion.click();
            Thread.sleep(2000);
            driver.findElement(agregarPagoButton).click();
            Thread.sleep(2000);
            driver.findElement(finalizarPagoButton).click();

            return !driver.findElements(codigoBarraBoleta).isEmpty(); // Verifica éxito con boleta
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }
}

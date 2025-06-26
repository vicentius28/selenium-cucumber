package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class VentasPage {
    WebDriver driver;
    private final String itemName = "TestItem2";

    By itemInput = By.id("item");
    By salesNavItem  = By.xpath("//*[@id='home_module_list']/div[7]");
    By quantityInput = By.xpath("//*[@id='cart_contents']/tr[1]/td[5]/input");
    By agregarPagoBtn = By.id("add_payment_button");
    By finalizarPagoButton = By.id("finish_sale_button");
    By codigoDeBarraBoleta = By.xpath("//*[@id='barcode']/img");
    By tipoPagoBtn = By.cssSelector("button[data-id='payment_types']");

    public VentasPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean loadVentas() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Espera hasta que el módulo de inicio esté visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("home_module_list")));

            // Luego espera a que el módulo de ventas (div[7]) esté presente y clickeable
            wait.until(ExpectedConditions.elementToBeClickable(salesNavItem)).click();

            return true;
        } catch (Exception e) {
            System.out.println("No se pudo cargar el módulo de ventas: " + e.getMessage());
            return false;
        }
    }


    public boolean agregaItem(){
        try {
            WebElement elementItemInput = driver.findElement(itemInput);
            elementItemInput.sendKeys(itemName);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ul[1]/li")));
            elementItemInput.sendKeys(Keys.DOWN,Keys.RETURN);
            driver.findElement(quantityInput).sendKeys(Keys.CONTROL+"a");
            driver.findElement(quantityInput).sendKeys(Keys.DELETE);
            driver.findElement(quantityInput).sendKeys("3");
            driver.findElement(quantityInput).sendKeys(Keys.ENTER); //Sin esto no toma los cambios el formulario para el calculo de precio
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }

    }

    public boolean pagar(String tipoPago) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By opcionPago = By.xpath("//div[@class='dropdown-menu open']//span[contains(text(), '"+ tipoPago + "')]");//Bootstrap hace que los divs sean input tipo select
        try {

            WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(tipoPagoBtn));
            dropdownButton.click();

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(opcionPago));
            option.click();

            driver.findElement(agregarPagoBtn).click();
            Thread.sleep(2000);
            driver.findElement(finalizarPagoButton).click();
            return !driver.findElements(codigoDeBarraBoleta).isEmpty(); //verifica si existe codigo de barra de boleta
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }


}

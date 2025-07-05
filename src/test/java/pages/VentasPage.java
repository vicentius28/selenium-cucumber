package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;
import java.time.Duration;
import java.util.List;

public class VentasPage {
    private final WebDriver driver;


    // Element locators en CamelCase
    private final By itemInput = By.id("item");
    private final By salesNavItem = By.xpath("//*[@id='home_module_list']/div[7]");
    private final By quantityInput = By.xpath("//*[@id='cart_contents']/tr[1]/td[5]/input");
    private final By suggestionItem = By.xpath("//ul[contains(@id,'ui-id')]/li");
    private final By discountInput = By.xpath("//input[@name='discount']");


    private final By agregarPagoButton = By.id("add_payment_button");
    private final By finalizarPagoButton = By.id("finish_sale_button");
    private final By codigoBarraBoleta = By.xpath("//*[@id='barcode']/img");
    private final By tipoPagoDropdown = By.cssSelector("button[data-id='payment_types']");
    private final By customerInput = By.id("customer");
    private final By customerSuggestion = By.xpath("//ul[contains(@id,'ui-id')]/li");
    private final By selectedCustomer = By.xpath("//a[@title='Update Customer']");
    private final By commentTextarea = By.id("comment");

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

    public boolean agregarItemAVenta(String nombreProducto, String cantidadStr, String descuentoStr) {
        try {
            int cantidad = Integer.parseInt(cantidadStr);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(itemInput));

            // Limpiar completamente el input
            input.click();
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.DELETE);
            input.sendKeys(nombreProducto);

            // Esperar a que aparezca la sugerencia
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionItem));

            // Seleccionar la sugerencia
            input.sendKeys(Keys.DOWN, Keys.RETURN);

            // Ingresar la cantidad
            WebElement cantidadInputEl = wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
            cantidadInputEl.sendKeys(Keys.CONTROL + "a");
            cantidadInputEl.sendKeys(Keys.DELETE);
            cantidadInputEl.sendKeys(String.valueOf(cantidad));
            cantidadInputEl.sendKeys(Keys.ENTER);

            // Ingresar descuento
            WebElement descuentoInputEl = wait.until(ExpectedConditions.elementToBeClickable(discountInput));
            descuentoInputEl.sendKeys(Keys.CONTROL + "a");
            descuentoInputEl.sendKeys(Keys.DELETE);
            descuentoInputEl.sendKeys(descuentoStr);
            descuentoInputEl.sendKeys(Keys.ENTER);

            Thread.sleep(1000); // Breve espera opcional para confirmar acción

            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar ítem con cantidad y descuento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(String nombreProducto) {
        try {
            List<WebElement> filas = driver.findElements(By.xpath("//tbody/tr"));
            for (WebElement fila : filas) {
                String textoFila = fila.getText();
                if (textoFila.contains(nombreProducto)) {
                    // Dentro de la fila encontrada, buscar el botón Delete (el span anidado)
                    WebElement botonDelete = fila.findElement(By.xpath(".//td[1]/span[1]/span[1]"));
                    botonDelete.click();
                    Utils.delay(1000);
                    return true;

                }
            }
            System.out.println("Producto no encontrado: " + nombreProducto);
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }


    public boolean agregarCliente(String nombreCliente) {
        try {
            Utils.delay(1000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(customerInput));

            // Limpiar el campo antes de escribir
            Utils.delay(2000);
            input.click();
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.DELETE);
            input.sendKeys(nombreCliente);

            // Esperar y seleccionar la sugerencia
            wait.until(ExpectedConditions.visibilityOfElementLocated(customerSuggestion));
            input.sendKeys(Keys.DOWN);
            input.sendKeys(Keys.RETURN);

            // Verificar que se muestra el cliente seleccionado (con enlace 'Update Customer')
            WebElement clienteSeleccionado = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedCustomer));
            return clienteSeleccionado.getText().toLowerCase().contains(nombreCliente.toLowerCase());

        } catch (Exception e) {
            System.out.println("Error al seleccionar cliente: " + e.getMessage());
            return false;
        }
    }



    public void seleccionarMetodoPagoYAgregar(String tipoPago) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By opcionPago = By.xpath("//div[@class='dropdown-menu open']//span[contains(text(), '" + tipoPago + "')]");
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(tipoPagoDropdown));
            dropdown.click();

            WebElement opcion = wait.until(ExpectedConditions.elementToBeClickable(opcionPago));
            opcion.click();
            Thread.sleep(1000);
            driver.findElement(agregarPagoButton).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error al seleccionar método de pago: " + e.getMessage());
        }
    }


    public void ingresarComentario(String comentario) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement commentBox = wait.until(ExpectedConditions.elementToBeClickable(commentTextarea));

            commentBox.click();
            commentBox.clear(); // Opcional: por si hay texto previo
            commentBox.sendKeys(comentario);

            System.out.println("Comentario ingresado correctamente.");
            Utils.delay(2000);
        } catch (Exception e) {
            System.out.println("Error al ingresar comentario: " + e.getMessage());
        }
    }

    public boolean finalizarVenta() {
        try {
            driver.findElement(finalizarPagoButton).click();

            // Espera de 3 segundos para visualizar la boleta o confirmación
            Utils.delay(3000);

            return !driver.findElements(codigoBarraBoleta).isEmpty();
        } catch (Exception e) {
            System.out.println("Error al finalizar venta: " + e.getMessage());
            return false;
        }
    }

}

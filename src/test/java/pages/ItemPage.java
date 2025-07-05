package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;
import java.time.Duration;

public class ItemPage {
    private final WebDriver driver;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By itemModule = By.xpath("//a[normalize-space()='Items']");
    private final By newItemButton = By.xpath("//button[@title='New Item']");

    private final By submitButton = By.id("submit");
    private final By successMessage = By.cssSelector(".alert-success"); // ajusta si es necesario

    private final By itemNameInput = By.id("name");
    private final By categoryInput = By.id("category"); // o el correcto si es diferente
    private final By itemPriceInput = By.id("cost_price");
    private final By retailPriceInput = By.id("unit_price");
    private final By quantityInput = By.id("quantity_1");
    private final By receivingInput = By.id("receiving_quantity");
    private final By reorderInput = By.id("reorder_level");
    private final By deleteButtonBy = By.xpath("//button[@id='delete']");




    public void irAModuloItems() {
        driver.findElement(itemModule).click();
    }

    public void abrirFormularioNuevoItem() {
        driver.findElement(newItemButton).click();
    }

    public void reabrirFormularioNuevoItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(newItemButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemNameInput));
    }
    public void registrarItemBasico(String nombre, String categoria, String costo, String precio, String stock, String recibidos, String nivelReorden) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(itemNameInput));
        nameInput.clear();
        nameInput.sendKeys(nombre);

        WebElement category = driver.findElement(categoryInput);
        category.clear();
        category.sendKeys(categoria);

        WebElement cost = driver.findElement(itemPriceInput);
        cost.clear();
        cost.sendKeys(costo);

        WebElement price = driver.findElement(retailPriceInput);
        price.clear();
        price.sendKeys(precio);

        WebElement quantity = driver.findElement(quantityInput);
        quantity.clear();
        quantity.sendKeys(stock);

        WebElement received = driver.findElement(receivingInput);
        received.clear();
        received.sendKeys(recibidos);

        WebElement reorder = driver.findElement(reorderInput);
        reorder.clear();
        reorder.sendKeys(nivelReorden);

        driver.findElement(submitButton).click();

        // Esperar mensaje de éxito y volver a abrir el formulario
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        reabrirFormularioNuevoItem();
    }




    public boolean mensajeExitoVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public void eliminarItemPorNombre(String nombreItem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Utils.delay(3000);

        String xpath = String.format("//tr[td[normalize-space()='%s']]//input[@type='checkbox']", nombreItem);
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        checkbox.click();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonBy));
        deleteButton.click();
        Utils.delay(2000);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}

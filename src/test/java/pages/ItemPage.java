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

    private final By itemsModuleLink = By.xpath("//a[normalize-space()='Items']");
    private final By newItemButton = By.xpath("//button[@title='New Item']");

    private final By submitButton = By.id("submit");
    private final By successAlert = By.cssSelector(".alert-success");

    private final By itemNameInput = By.id("name");
    private final By categoryInput = By.id("category"); // o el correcto si es diferente
    private final By itemPriceInput = By.id("cost_price");
    private final By retailPriceInput = By.id("unit_price");
    private final By stockQuantityInput = By.id("quantity_1");
    private final By receivingInput = By.id("receiving_quantity");
    private final By reorderInput = By.id("reorder_level");
    private final By deleteButtonById = By.xpath("//button[@id='delete']");
    private final By deleteSuccessAlert = By.cssSelector("div[role='alert']");


    public void goToItemsModule() {
        driver.findElement(itemsModuleLink).click();
    }

    public void openNewItemForm() {
        driver.findElement(newItemButton).click();
    }


    public void registerBasicItem(String nombre, String categoria, String costo, String precio, String stock, String recibidos, String nivelReorden) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        utils.Utils.delay(2000);
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

        WebElement quantity = driver.findElement(stockQuantityInput);
        quantity.clear();
        quantity.sendKeys(stock);

        WebElement received = driver.findElement(receivingInput);
        received.clear();
        received.sendKeys(recibidos);

        WebElement reorder = driver.findElement(reorderInput);
        reorder.clear();
        reorder.sendKeys(nivelReorden);

        driver.findElement(submitButton).click();
        Utils.delay(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
    }

    public boolean isSuccessMessageVisible() {
        return driver.findElement(successAlert).isDisplayed(

        );
    }

    public void deleteItemByName(String nombreItem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Utils.delay(3000);

        String xpath = String.format("//tr[td[normalize-space()='%s']]//input[@type='checkbox']", nombreItem);
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        checkbox.click();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonById));
        deleteButton.click();
        Utils.delay(2000);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public boolean isDeleteSuccessVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteSuccessAlert));
        return alert.getText().contains("successfully deleted");
    }

}

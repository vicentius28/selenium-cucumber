package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;


public class RecievingsPage {

    private final WebDriver driver;
    public RecievingsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By recievingModuleLink = By.xpath("//a[normalize-space()='Receivings']");
    private final By itemInput = By.id("item");
    private final By suggestionItem = By.xpath("//ul[contains(@id,'ui-id')]/li");

    private final By commentTextarea = By.id("comment");
    private final By quantityInput = By.xpath("//*[@id='cart_contents']/tr[1]/td[5]/input");
    private final By paymentTypeInput = By.cssSelector("button[data-id='payment_types']");
    private final By shipPackInput = By.name("receiving_quantity");
    private final By finishRegister = By.id("finish_receiving_button");
    private final By ammountTendered = By.name("amount_tendered");
    private final By supplierInput = By.name("supplier");
    private final By suggestionSupplier = By.xpath("//*[@id='ui-id-2']");
    private final By barCodeBill = By.xpath("//*[@id='barcode']/img");


    public boolean goToSuppliersModule() {
        System.out.println("Se ingresa a modulo de recepciones");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(recievingModuleLink)).click();
        } catch (Exception e) {
            System.out.println("No se pudo cargar el módulo de recepcion: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addRecievedItem(String item, String quantity, String ShipPack){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try{
            System.out.println("Se ingresa item: "+item);
            WebElement itemInputEl = wait.until(ExpectedConditions.elementToBeClickable(itemInput));
            itemInputEl.click();
            itemInputEl.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            itemInputEl.sendKeys(item);

            // Esperar a que aparezca la sugerencia
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionItem));
            itemInputEl.sendKeys(Keys.DOWN, Keys.RETURN);


            WebElement quantityInputEl = wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
            quantityInputEl.click();
            quantityInputEl.sendKeys(Keys.CONTROL+"a", Keys.DELETE);
            quantityInputEl.sendKeys(quantity, Keys.ENTER);

            WebElement shipPackInputEl = wait.until(ExpectedConditions.elementToBeClickable(shipPackInput));
            Select selectShipPack =  new Select(shipPackInputEl);
            shipPackInputEl.click();
            selectShipPack.selectByVisibleText(ShipPack);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("No se pudo agregar item " + item +": " + e.getMessage());
            return false;
        }
        return true;

    }


    public boolean prepareRecieving(String supplier,String paymentType, String monto, String comment){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By paymentSelection = By.xpath("//div[@class='dropdown-menu open']//span[contains(text(), '" + paymentType + "')]");
        try {
            WebElement supplierEl = wait.until(ExpectedConditions.visibilityOfElementLocated(supplierInput));
            supplierEl.click();
            supplierEl.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
            supplierEl.sendKeys(supplier);

            // Esperar a que aparezca la sugerencia
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionSupplier));
            supplierEl.sendKeys(Keys.DOWN, Keys.ENTER);

            WebElement commentEl = wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea));
            commentEl.sendKeys(comment, Keys.ENTER);


            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(paymentTypeInput));
            dropdown.click();
            WebElement selection = wait.until(ExpectedConditions.elementToBeClickable(paymentSelection));
            selection.click();

            WebElement ammountTenderedEl = wait.until(ExpectedConditions.elementToBeClickable(ammountTendered));
            ammountTenderedEl.click();
            ammountTenderedEl.sendKeys(monto, Keys.ENTER);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("No se pudo registrar recepcion, tipode pago " + paymentType +": " + e.getMessage());
            return false;
        }

        return true;
    }
    public boolean registerRecieving(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
            driver.findElement(finishRegister).click();
            //Utils.delay(1000);
            WebElement barcodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(barCodeBill));
            return barcodeElement != null;
        }
        catch (TimeoutException te){
            System.out.println("Error al generar boleta: " + te.getMessage());
            return false;
        }
        catch (Exception e) {
            System.out.println("Error al finalizar registro: " + e.getMessage());
            return false;
        }
    }
}

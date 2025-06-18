package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class VentasPage {
    WebDriver driver;
    private final String username = "admin", password  = "pointofsale";
    private String itemName = "TestItem2";
    By itemInput = By.id("item");
    By salesNavItem  = By.xpath("//*[@id='home_module_list']/div[7]");
    By quantityInput = By.xpath("//*[@id='cart_contents']/tr[1]/td[5]/input");
    By tipoPagoBtn = By.xpath("//*[@id='add_payment_form']/table/tbody/tr[1]/td[2]/div"); //Bootstrap hace que los divs sean input tipo select
    By agregarPagoBtn = By.id("add_payment_button");
    By finalizarPagoButton = By.id("finish_sale_button");
    By codigoDeBarraBoleta = By.xpath("//*[@id='barcode']/img");
    public VentasPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(){
        driver.get("http://localhost/ospos/public/login");
        driver.findElement(By.id("input-username")).sendKeys(username);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.name("login-button")).click();
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

    public boolean pagar() {

        try {
            driver.findElement(tipoPagoBtn).click();
            driver.findElement(By.id("bs-select-2-1")).click();

            //selectPayment.selectByVisibleText("Debit Card");
            List<WebElement> list = driver.findElements(By.xpath("//*[@id='bs-select-2']/ul"));
            for (WebElement ele : list)
            {
                System.out.println("Values " + ele.getAttribute("innerHTML"));

                if (ele.getAttribute("innerHTML").contains("JavaScript")) {
                    ele.click();

                    break;

                }

            }
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

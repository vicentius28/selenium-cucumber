package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;


public class SupplierPage {
    private final WebDriver driver;

    public SupplierPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By supplierModule = By.xpath("//a[normalize-space()='Suppliers']");
    private final By newSupplierButton = By.cssSelector("button[title='New Supplier']");
    private final By submitButton = By.id("submit");

    private final By companyNameInput = By.id("company_name_input");
    private final By categorySelect = By.id("category");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By successMessage = By.cssSelector(".alert-success");
    private final By deleteButtonBy = By.xpath("//button[@id='delete']");



    public void irAModuloSuppliers() {
        driver.findElement(supplierModule).click();
    }

    public void abrirFormularioNuevoSupplier() {
        driver.findElement(newSupplierButton).click();
    }

    public void registrarSupplierBasico(String company, String categoria, String nombre, String apellido) {
        driver.findElement(companyNameInput).sendKeys(company);
        new Select(driver.findElement(categorySelect)).selectByVisibleText(categoria);
        driver.findElement(firstNameInput).sendKeys(nombre);
        driver.findElement(lastNameInput).sendKeys(apellido);
        Utils.delay(3000);
        driver.findElement(submitButton).click();
    }

    public boolean mensajeExitoVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public void eliminarSupplierPorCompany(String companyItem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Utils.delay(3000);

        String xpath = String.format("//tr[td[normalize-space()='%s']]//input[@type='checkbox']", companyItem);
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        checkbox.click();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonBy));
        deleteButton.click();
        Utils.delay(2000);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }


}

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

    private final By suppliersModuleLink = By.xpath("//a[normalize-space()='Suppliers']");
    private final By newSupplierButton = By.cssSelector("button[title='New Supplier']");
    private final By saveSupplierButton = By.id("submit");
    private final By companyNameInput = By.id("company_name_input");
    private final By categorySelect = By.id("category");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By successAlert = By.cssSelector(".alert-success");
    private final By deleteSupplierButton = By.xpath("//button[@id='delete']");
    private final By deleteSuccessAlert = By.cssSelector("div[role='alert']");


    public void goToSuppliersModule() {
        driver.findElement(suppliersModuleLink).click();
    }

    public void openNewSupplierForm() {
        driver.findElement(newSupplierButton).click();
    }

    public void registerBasicSupplier(String company, String categoria, String nombre, String apellido) {
        driver.findElement(companyNameInput).sendKeys(company);
        new Select(driver.findElement(categorySelect)).selectByVisibleText(categoria);
        driver.findElement(firstNameInput).sendKeys(nombre);
        driver.findElement(lastNameInput).sendKeys(apellido);
        Utils.delay(3000);
        driver.findElement(saveSupplierButton).click();
    }

    public boolean isSuccessAlertVisible() {
        return driver.findElement(successAlert).isDisplayed();
    }

    public void deleteSupplierByCompanyName(String companyItem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Utils.delay(3000);

        String xpath = String.format("//tr[td[normalize-space()='%s']]//input[@type='checkbox']", companyItem);
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        checkbox.click();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteSupplierButton));
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

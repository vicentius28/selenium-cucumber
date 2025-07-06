package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

public class CustomerPage {
    private final WebDriver driver;

    private final By customersModuleLink = By.xpath("//a[normalize-space()='Customers']");
    private final By newCustomerButton = By.cssSelector("button[title='New Customer']");
    private final By firstNameInput = By.xpath("//input[@id='first_name']");
    private final By lastNameInput = By.xpath("//input[@id='last_name']");
    private final By saveCustomerButton = By.xpath("//button[@id='submit']");
    private final By errorFirstName = By.cssSelector("label#first_name-error.has-error");
    private final By errorLastName = By.cssSelector("label#last_name-error.has-error");
    private final By deleteCustomerButton = By.xpath("//button[@id='delete']");


    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openNewCustomerForm() {
        driver.findElement(newCustomerButton).click();
    }

    public void clickModuleCustomer() {
        driver.findElement(customersModuleLink).click();
    }

    public void registerCustomer(String name, String lastName) {
        driver.findElement(firstNameInput).sendKeys(name);
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void submitForm() {
        driver.findElement(saveCustomerButton).click();
        Utils.delay(4000);
    }

    public boolean isFirstNameErrorVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorFirstName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public boolean isLastNameErrorVisible() {
        return !driver.findElements(errorLastName).isEmpty();
    }

    public boolean isCustomerRegistered() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("span[data-notify='message']")))
                .isDisplayed();
    }

    public void selectCustomer(String nombre, String apellido) {
        String xpath = String.format("//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]//input[@type='checkbox']", apellido, nombre);
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        checkbox.click();
    }

    public void clickDeleteCustomer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteCustomerButton));
        deleteButton.click();
    }


    public boolean isCustomerDeleted(String nombre, String apellido) {
        String xpath = String.format("//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]", nombre, apellido);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        try {
            System.out.println("Esperando que el cliente ya no esté visible...");
            boolean desaparecio = wait.until(driver -> driver.findElements(By.xpath(xpath)).isEmpty());
            System.out.println("¿Cliente eliminado?: " + desaparecio);
            return desaparecio;
        } catch (Exception e) {
            System.out.println("Excepción al verificar si cliente fue eliminado: " + e.getMessage());
            return false;
        }
    }




}

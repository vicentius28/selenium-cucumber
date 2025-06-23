package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerPage {
    private final WebDriver driver;

    private final By customerModule = By.xpath("//a[normalize-space()='Customers']");
    private final By newCustomerButton = By.cssSelector("button[title='New Customer']");
    private final By firstNameInput = By.xpath("//input[@id='first_name']");
    private final By lastNameInput = By.xpath("//input[@id='last_name']");
    private final By submitButton = By.xpath("//button[@id='submit']");
    private final By errorFirstName = By.xpath("//label[@id='first_name-error']");
    private final By errorLastName = By.xpath("//label[@id='last_name-error']");

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openNewCustomerForm() {
        driver.findElement(newCustomerButton).click();
    }

    public void clickModuleCustomer() {
        driver.findElement(customerModule).click();
    }

    public void enterFirstName(String name) {
        driver.findElement(firstNameInput).sendKeys(name);
    }

    public void enterLastName(String name) {
        driver.findElement(lastNameInput).sendKeys(name);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public boolean isFirstNameErrorVisible() {
        return !driver.findElements(errorFirstName).isEmpty();
    }

    public boolean isLastNameErrorVisible() {
        return !driver.findElements(errorLastName).isEmpty();
    }
}

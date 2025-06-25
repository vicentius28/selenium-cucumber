package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage {
    private final WebDriver driver;

    private final By customerModule = By.xpath("//a[normalize-space()='Customers']");
    private final By newCustomerButton = By.cssSelector("button[title='New Customer']");
    private final By firstNameInput = By.xpath("//input[@id='first_name']");
    private final By lastNameInput = By.xpath("//input[@id='last_name']");
    private final By submitButton = By.xpath("//button[@id='submit']");
    private final By errorFirstName = By.xpath("//label[@id='first_name-error']");
    private final By errorLastName = By.xpath("//label[@id='last_name-error']");
    private final By successMessage = By.cssSelector("span[data-notify='message'']");


    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openNewCustomerForm() {
        driver.findElement(newCustomerButton).click();
    }

    public void clickModuleCustomer() {
        driver.findElement(customerModule).click();
    }

    public void registerCustomer(String name, String lastName) {
        driver.findElement(firstNameInput).sendKeys(name);
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public boolean isFirstNameErrorVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorFirstName)).isDisplayed();
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
}

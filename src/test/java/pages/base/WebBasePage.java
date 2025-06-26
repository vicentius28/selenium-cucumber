package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebBasePage {

    protected WebDriver driver;

    public WebBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementIsVisibleNonThrow(WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Elemento no visible tras " + timeoutSeconds + " segundos: " + e.getMessage());
        }
    }

    public void clickElement(WebElement element, int timeoutSeconds) {
        waitUntilElementIsVisibleNonThrow(element, timeoutSeconds);
        element.click();
    }

    public boolean isElementVisible(WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

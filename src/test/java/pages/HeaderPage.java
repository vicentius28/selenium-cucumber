package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage {
    private final WebDriverWait wait;
    private final By logoutButton = By.xpath("//div[@class='navbar-right']//a[text()='Logout']");

    public HeaderPage(WebDriver driver) {
        this(driver, 10); // usa por defecto 10 segundos
    }

    public HeaderPage(WebDriver driver, int timeoutInSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // Selectores
    private final By usernameInput = By.id("input-username");
    private final By passwordInput = By.id("input-password");
    private final By loginButton = By.name("login-button");
    private final By dashboardModuleList = By.id("home_module_list");
    private final By loginError = By.cssSelector(".alert-danger");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Abre la página de login
    public void open() {
        driver.get("http://localhost/ospos/public/login");
    }

    // Login con credenciales personalizadas
    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isLoginErrorVisible() {
        return !driver.findElements(loginError).isEmpty();
    }

}

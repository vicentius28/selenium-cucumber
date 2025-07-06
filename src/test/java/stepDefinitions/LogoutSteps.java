package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.HeaderPage;

import java.time.Duration;


public class LogoutSteps {

    private final WebDriver driver = Hooks.getDriver();

    @When("cierra sesión")
    public void cierraSesion() {
        HeaderPage headerPage = new HeaderPage(driver);
        try {
            headerPage.logout();
        } catch (Exception e) {
            System.out.println("No se pudo cerrar sesión: " + e.getMessage());
        }
    }

    @Then("vuelve a la página de login")
    public void vuelveALaPaginaDeLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean campoLoginVisible = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("input-username")))
                .isDisplayed();

        Assert.assertTrue("No se redirigió correctamente al login", campoLoginVisible);
    }
}

package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private final WebDriver driver = Hooks.getDriver();
    private LoginPage loginPage;

    private void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Delay interrumpido: " + e.getMessage());
        }
    }


    @Given("el usuario está en la página de login")
    public void usuarioEnPaginaLogin() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @When("ingresa credenciales válidas")
    public void ingresarCredencialesValidas() {
        loginPage.loginAsAdmin();
    }

    @When("ingresa credenciales inválidas")
    public void ingresarCredencialesInvalidas() {
        loginPage.login("admin", "clave_incorrecta");
    }

    @Then("accede al sistema correctamente")
    public void accesoCorrectoAlDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        boolean textoVisible = wait.until(ExpectedConditions
                .textToBePresentInElementLocated(
                        By.cssSelector("h3.text-center"),
                        "Welcome to OSPOS"
                ));
        delay();
        Assert.assertTrue("No se accedió correctamente al dashboard", textoVisible);
    }



    @Then("se muestra un mensaje de error")
    public void mostrarMensajeDeError() {
        assertTrue("No se mostró mensaje de error", loginPage.isLoginErrorVisible());
    }
}

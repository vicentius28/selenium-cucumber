package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import io.cucumber.java.ro.Cand;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class LoginSteps {

    WebDriver driver = Hooks.getDriver();

    @Given("el usuario está en la página de login")
    public void el_usuario_esta_en_login() {
        driver.get("http://localhost/ospos/public/login");
        driver.manage().window().setSize(new Dimension(1054, 800));
    }

    @When("ingresa credenciales válidas")
    public void ingresa_credenciales_validas() {
        driver.findElement(By.id("input-username")).sendKeys("admin");
        driver.findElement(By.id("input-password")).sendKeys("pointofsale");
        driver.findElement(By.name("login-button")).click();
    }

    @Then("accede al sistema correctamente")
    public void accede_sistema_correctamente() {
        boolean apareceLogout = driver.findElement(By.linkText("Logout")).isDisplayed();
        Assert.assertTrue("No se logró acceder correctamente al sistema", apareceLogout);
    }
    @And("cierra sesión")
    public void cerrar_sesion() {
        driver.findElement(By.linkText("Logout")).click();
    }


}

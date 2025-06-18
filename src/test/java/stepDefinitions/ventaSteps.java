package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import hooks.Hooks;
import pages.LoginPage;
import pages.VentasPage;

public class ventaSteps {

    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    VentasPage ventasPage = new VentasPage(driver);

    @Given("ingreso a la pagina de inicial de ospos")
    public void ingreso_a_la_pagina_de_inicial_de_ospos() {
        System.out.println("➡️ Iniciando login en OSPOS");
        loginPage.open();
        loginPage.loginAsAdmin(); // ✅ aquí va el login real
    }

    @When("ingreso a la pagina de ventas")
    public void ingreso_a_la_pagina_de_ventas() {
        System.out.println("➡️ Navegando a módulo Ventas");
        Assert.assertTrue("❌ No se pudo acceder al módulo de ventas", ventasPage.loadVentas());
    }

    @Then("agrega item a venta")
    public void agregaItemVenta() {
        Assert.assertTrue(ventasPage.agregaItem());
    }
    @And("ejecuta pago")
    public void ejecutaPago() throws InterruptedException {
        Assert.assertTrue(ventasPage.pagar());
    }


}

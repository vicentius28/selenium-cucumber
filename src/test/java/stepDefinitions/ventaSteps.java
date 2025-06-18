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

    // Puedes reactivar los siguientes pasos si los vas a usar:
    /*
    @When("realizo la venta con la condicion")
    public void realizo_la_venta_con_la_condicion() {
        System.out.println("Simular venta");
    }

    @When("..... esta accion se encuentra en el feature  {string}")
    public void esta_accion_se_encuentra_en_el_feature(String detalle) {
        System.out.println("Acción desde feature: " + detalle);
    }

    @Then("aqui pasa la condicion que estamos validando")
    public void aqui_pasa_la_condicion_que_estamos_validando() {
        System.out.println("Validación exitosa");
    }

    @Then("..... otras acciones secundarias")
    public void otras_acciones_secundarias() {
        System.out.println("Validaciones secundarias");
    }
    */
}

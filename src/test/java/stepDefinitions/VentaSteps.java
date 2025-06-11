package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.VentasPage;
import org.junit.Assert;
import hooks.Hooks;

public class VentaSteps {

    WebDriver driver = Hooks.getDriver();
    VentasPage ventasPage = new VentasPage(driver);
    @Given("ingreso a la pagina de inicial de ospos")
    public void ingreso_a_la_pagina_de_inicial_de_ospos() {

        System.out.println("Abrir OSPOS");
        ventasPage.login();

    }

    @When("ingreso a la pagina de ventas")
    public void ingreso_a_la_pagina_de_ventas(){
        Assert.assertTrue(ventasPage.loadVentas());
    }

    /*
    @When("realizo la venta con la condicion")
    public void realizo_la_venta_con_la_condicion() {
        // Aquí iría la lógica de agregar productos al carrito, por ejemplo
        System.out.println("Simular venta");
    }

    @When("..... esta accion se encuentra en el feature  {string}")
    public void esta_accion_se_encuentra_en_el_feature(String detalle) {
        System.out.println("Acción desde feature: " + detalle);
    }

    @Then("aqui pasa la condicion que estamos validando")
    public void aqui_pasa_la_condicion_que_estamos_validando() {
        // Validación final
        System.out.println("Validación exitosa");
    }

    @Then("..... otras acciones secundarias")
    public void otras_acciones_secundarias() {
        // Validaciones adicionales
        System.out.println("Validaciones secundarias");
    }
    */

}

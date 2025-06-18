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

    @Then("agrega item a venta")
    public void agregaItemVenta() {
        Assert.assertTrue(ventasPage.agregaItem());
    }
    @And("ejecuta pago")
    public void ejecutaPago() throws InterruptedException {
        Assert.assertTrue(ventasPage.pagar());
    }


}

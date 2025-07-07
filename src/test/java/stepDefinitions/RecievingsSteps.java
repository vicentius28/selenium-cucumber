package stepDefinitions;

import io.cucumber.java.en.*;
import driver.DriverFactory;
import io.cucumber.java.en_scouse.An;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.RecievingsPage;

public class RecievingsSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final RecievingsPage recievingsPage = new RecievingsPage(driver);

    @Given("el usuario ingresa al modulo recepcion")
    public void elUsuarioIngresaAlModuloRecepcion(){
        Assert.assertTrue("No se pudo cargar el módulo de Recepciones", recievingsPage.goToSuppliersModule());
    }

    @And("el usuario agrega el producto {string} con cantidad {string} y tipo paquete {string}")
    public void elUsuarioAgregaElProductoCantidadYTipoPaquete(String item, String quantity, String shipPack){
        Assert.assertTrue("No se pudo agregar la recepcion con cantidad", recievingsPage.addRecievedItem(item,quantity, shipPack));
    }
    @Then("el usuario llena formluario de pago con proveedor {string}, tipo de pago {string}, monto {string} y comentario {string}")
    public void elUsuarioRegistraLlenaFormluarioDePago(String supplier, String paymentType, String monto, String comment){
        Assert.assertTrue("no se pudo llenar informacion de pago",
                recievingsPage.prepareRecieving(supplier, paymentType, monto,comment));
    }

    @And("el usuario finaliza registro de recepcion")
    public void elUsuarioFInalizaRegistroRecepcion(){
        Assert.assertTrue("no se pudo registra recepcion", recievingsPage.registerRecieving());
    }

}

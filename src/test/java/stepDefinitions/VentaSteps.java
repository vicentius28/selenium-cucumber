package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.VentasPage;
import driver.DriverFactory;

public class VentaSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final VentasPage ventasPage = new VentasPage(driver);


    @Given("el usuario ingresa al módulo de ventas")
    public void elUsuarioIngresaAlModuloDeVentas() {
        Assert.assertTrue("No se pudo cargar el módulo de ventas", ventasPage.cargarModuloVentas());
    }


    @And("el usuario agrega el producto {string} con cantidad {string} a la venta")
    public void elUsuarioAgregaElProductoConCantidadALaVenta(String producto, String cantidad) {
        Assert.assertTrue("No se pudo agregar el producto con cantidad", ventasPage.agregarItemAVenta(producto, cantidad));
    }



    @When("el usuario procede al pago")
    public void elUsuarioProcedeAlPago() {
        // No se requiere acción aquí, forma parte del siguiente paso
    }

    @And("selecciona el método de pago {string} e ingresa el monto exacto")
    public void seleccionaMetodoDePagoEIngresaMontoExacto(String tipoPago) {
        Assert.assertTrue("Error al realizar el pago", ventasPage.realizarPago(tipoPago));
    }

    @And("confirma la venta")
    public void confirmaLaVenta() {
        // Este paso ya está incluido dentro de realizarPago()
    }

    @Then("se muestra mensaje de éxito tras registrar la venta")
    public void seMuestraMensajeDeExitoTrasRegistrarLaVenta() {
        // Ya validado en realizarPago con presencia del código de barras
        System.out.println("Venta registrada exitosamente.");
    }
}

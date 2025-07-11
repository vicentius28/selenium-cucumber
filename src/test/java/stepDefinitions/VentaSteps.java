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


    @And("el usuario agrega el producto {string} con cantidad {string} y dscto {string} a la venta")
    public void elUsuarioAgregaElProductoConCantidadALaVenta(String producto, String cantidad, String dscto) {
        Assert.assertTrue("No se pudo agregar el producto con cantidad", ventasPage.agregarItemAVenta(producto, cantidad, dscto));
    }

    @And("el usuario elimina el producto {string} de la venta")
    public void elUsuarioEliminaElProductoDeLaVenta(String nombreProducto) {
        Assert.assertTrue("No se pudo eliminar el producto: " + nombreProducto,
                ventasPage.eliminarProducto(nombreProducto));
    }

    @And("el usuario selecciona el cliente {string}")
    public void elUsuarioSeleccionaElCliente(String nombreCliente) {
        Assert.assertTrue("No se pudo seleccionar el cliente", ventasPage.agregarCliente(nombreCliente));
    }

    @And ("el usuario ingresa a las ventas diarias")
    public void elUsuarioIngresaALasVentasDiarias(){
        ventasPage.clickDailySales();
    }

    @And("selecciona la venta con el cliente {string} y amount {string}")
    public void seleccionaLaVentaConFechaClienteYMonto(String customer, String amount){
        ventasPage.selectSales(customer, amount);
    }


    @When("el usuario procede al pago")
    public void elUsuarioProcedeAlPago() {
        // No se requiere acción aquí, forma parte del siguiente paso
    }

    @When ("presiona el botón de anular venta")
    public void presionaElBotonDeAnularVenta(){
        ventasPage.clickDeleteSales();
    }

    @And("selecciona el método de pago {string} e ingresa el monto exacto")
    public void seleccionarMetodoPago(String tipoPago) {
        ventasPage.seleccionarMetodoPagoYAgregar(tipoPago);
    }


    @And("confirma la venta")
    public void confirmarVenta() {
        Assert.assertTrue("La venta no se completó con éxito", ventasPage.finalizarVenta());
    }


    @And("el usuario ingresa el comentario {string}")
    public void elUsuarioIngresaComentario(String comentario) {
        ventasPage.ingresarComentario(comentario);
    }


    @Then("se muestra mensaje de éxito tras registrar la venta")
    public void seMuestraMensajeDeExitoTrasRegistrarLaVenta() {
        // Ya validado en realizarPago con presencia del código de barras
        System.out.println("Venta registrada exitosamente.");
    }

    @Then("acepta el mensaje de confirmación de eliminación de Ventas")
    public void aceptaElMensajeDeConfirmacionDeEliminacionDeVentas(){
        Assert.assertTrue("No apareció el mensaje de éxito", ventasPage.isDeleteSuccessVisible());
    }
}

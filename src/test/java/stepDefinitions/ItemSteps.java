package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ItemPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;

public class ItemSteps {
    private final WebDriver driver = DriverFactory.getDriver();
    private final ItemPage itemPage = new ItemPage(driver);

    @Given("el usuario ingresa al módulo items")
    public void elUsuarioIngresaAlModuloItems() {
        itemPage.goToItemsModule();
    }

    @And("el usuario abre el formulario de nuevo ítem")
    public void elUsuarioAbreElFormularioDeNuevoItem() {
        itemPage.openNewItemForm();
    }

    @When("el usuario ingresa nombre {string}, categoría {string}, costo {string}, precio {string}, stock {string}, recibidos {string} y nivel de reorden {string} y presiona submit")
    public void elUsuarioIngresaNombreCategoriaCostoPrecioStockRecibidosNivelDeReordenYPresionaSubmit(String nombre, String categoria, String costo, String precio, String stock, String recibidos, String nivelReorden) {
        itemPage.registerBasicItem(nombre, categoria, costo, precio, stock, recibidos, nivelReorden);
    }

    @And("elimina el item con nombre {string}")
    public void eliminaElItemConNombre(String nombre) {
        itemPage.deleteItemByName(nombre);
    }

    @Then("se muestra mensaje de éxito tras registro")
    public void seMuestraMensajeDeExitoTrasRegistro() {
        Assert.assertTrue("No se muestra mensaje de éxito", itemPage.isSuccessMessageVisible());
    }

    @Then("se muestra mensaje de éxito tras eliminación")
    public void seMuestraMensajeDeExitoTrasEliminacion() {
        Assert.assertTrue("No apareció el mensaje de éxito", itemPage.isDeleteSuccessVisible());
    }
}

package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.ItemPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;

public class ItemSteps {
    private final WebDriver driver = DriverFactory.getDriver();
    private final ItemPage itemPage = new ItemPage(driver);

    @And("el usuario ingresa al módulo items")
    public void ingresaModuloItems() {
        itemPage.irAModuloItems();
    }

    @And("el usuario abre el formulario de nuevo ítem")
    public void abrirFormularioNuevoItem() {
        itemPage.abrirFormularioNuevoItem();
    }

    @And("el usuario ingresa nombre {string}, categoría {string}, costo {string}, precio {string}, stock {string}, recibidos {string} y nivel de reorden {string} y presiona submit")
    public void registrarItemBasico(String nombre, String categoria, String costo, String precio, String stock, String recibidos, String nivelReorden) {
        itemPage.registrarItemBasico(nombre, categoria, costo, precio, stock, recibidos, nivelReorden);
    }

    @And("elimina el item con nombre {string}")
    public void eliminarItemConNombre(String nombre) {
        itemPage.eliminarItemPorNombre(nombre);
    }

    @Then("se muestra mensaje de éxito tras registro")
    public void mensajeExito() {
        Assert.assertTrue("No se muestra mensaje de éxito", itemPage.mensajeExitoVisible());
    }
}

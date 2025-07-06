package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SupplierPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;

public class SupplierSteps {
    private final WebDriver driver = DriverFactory.getDriver();
    private final SupplierPage supplierPage = new SupplierPage(driver);

    @Given("el usuario ingresa al módulo suppliers")
    public void elUsuarioIngresaAlModuloSuppliers() {
        supplierPage.goToSuppliersModule();
    }

    @And("el usuario abre el formulario de nuevo supplier")
    public void elUsuarioAbreElFormularioDeNuevoSupplier() {
        supplierPage.openNewSupplierForm();
    }

    @When("el usuario ingresa company {string}, categoría {string}, nombre {string}, apellido {string} y presiona submit")
    public void elUsuarioIngresaCompanyCategoriaNombreApellidoYPresionaSubmit(String company, String categoria, String nombre, String apellido) {
        supplierPage.registerBasicSupplier(company, categoria, nombre, apellido);
    }
    @When("elimina el proveedor {string}")
    public void eliminarAlProveedor(String proveedor) {
        supplierPage.deleteSupplierByCompanyName(proveedor);
    }

    @Then("se muestra mensaje de éxito tras registrar supplier")
    public void seMuestraMensajeDeExitoTrasRegistrarSupplier() {
        Assert.assertTrue("No se muestra mensaje de éxito en supplier", supplierPage.isSuccessAlertVisible());
    }

    @Then("se muestra mensaje de éxito tras eliminación de suppliers")
    public void seMuestraMensajeDeExitoTrasEliminacionDeSupplier(){
        Assert.assertTrue("No apareció el mensaje de éxito", supplierPage.isDeleteSuccessVisible());
    }
}

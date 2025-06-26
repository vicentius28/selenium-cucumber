package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SupplierPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;

public class SupplierSteps {
    private final WebDriver driver = DriverFactory.getDriver();
    private final SupplierPage supplierPage = new SupplierPage(driver);

    @And("el usuario ingresa al módulo suppliers")
    public void ingresarModuloSuppliers() {
        supplierPage.irAModuloSuppliers();
    }

    @And("el usuario abre el formulario de nuevo supplier")
    public void abrirFormularioNuevoSupplier() {
        supplierPage.abrirFormularioNuevoSupplier();
    }

    @And("el usuario ingresa company {string}, categoría {string}, nombre {string}, apellido {string} y presiona submit")
    public void registrarSupplierBasico(String company, String categoria, String nombre, String apellido) {
        supplierPage.registrarSupplierBasico(company, categoria, nombre, apellido);
    }
    @And("elimina el proveedor {string}")
    public void eliminarSupplierConCompany(String proveedor) {
        supplierPage.eliminarSupplierPorCompany(proveedor);
    }

    @Then("se muestra mensaje de éxito tras registrar supplier")
    public void mensajeExitoSupplier() {
        Assert.assertTrue("No se muestra mensaje de éxito en supplier", supplierPage.mensajeExitoVisible());
    }
}

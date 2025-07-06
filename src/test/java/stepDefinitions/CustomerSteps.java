package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CustomerPage;
import utils.Utils;

import static org.junit.Assert.assertTrue;

public class CustomerSteps {

    WebDriver driver = Hooks.getDriver();
    CustomerPage customerPage = new CustomerPage(driver);

    @Given("el usuario ingresa al modulo customers")
    public void elUsuarioIngresaAlModuloCustomers() {
        customerPage.clickModuleCustomer();
    }

    @And("el usuario abre el formulario de nuevo cliente")
    public void elUsuarioAbreElFormularioDeNuevoCliente() {
        customerPage.openNewCustomerForm();
    }

    @When("el usuario deja los campos obligatorios vacios y presiona submit")
    public void elUsuarioDejaLosCamposObligatoriosVaciosYPresionaSubmit() {
        customerPage.submitForm();
    }

    @When("el usuario ingresa su nombre {string}, apellido {string} y presiona submit")
    public void elUsuarioIngresaSuNombreApellidoYPresionaSubmit(String nombre, String apellido){
        customerPage.registerCustomer(nombre, apellido);
        customerPage.submitForm();
    }

    @And("selecciona al cliente con nombre {string} y apellido {string}")
    public void seleccionaAlClienteConNombreYApellido(String nombre, String apellido) {
        customerPage.selectCustomer(nombre, apellido);
        Utils.delay(3000);
    }

    @When("presiona el botón de eliminar cliente")
    public void presionarElBotonDeEliminarCliente() {
        customerPage.clickDeleteCustomer();
        Utils.delay(2000);
    }

    @And("acepta el mensaje de confirmación")
    public void aceptaElMensajeDeConfirmacion() {
        driver.switchTo().alert().accept();
        Utils.delay(3000);
    }


    @Then("se muestran errores por campos obligatorios")
    public void seMuestranErroresPorCamposObligatorios() {
        boolean firstNameError = customerPage.isFirstNameErrorVisible();
        boolean lastNameError = customerPage.isLastNameErrorVisible();

        System.out.println("Error visible para First Name: " + firstNameError);
        System.out.println("Error visible para Last Name: " + lastNameError);
        assertTrue("No aparece error por First Name", firstNameError);
        assertTrue("No aparece error por Last Name", lastNameError);

        System.out.println("Validación de errores obligatorios completada con éxito.");
    }

    @Then("se muestra mensaje de exito tras registro")
    public void seMuestraMensajeDeExitoTrasRegistro(){
        assertTrue(customerPage.isCustomerRegistered());
    }

    @Then("el cliente con nombre {string} y apellido {string} ya no está en la lista")
    public void elClienteConNombreYApellidoYaNoEstaEnLaLista(String nombre, String apellido) {
        assertTrue("El cliente aún está en la lista", customerPage.isCustomerDeleted(nombre, apellido));
    }
}

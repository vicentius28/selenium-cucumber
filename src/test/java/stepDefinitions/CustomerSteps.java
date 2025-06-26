package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CustomerPage;

import static org.junit.Assert.assertTrue;

public class CustomerSteps {

    WebDriver driver = Hooks.getDriver();
    CustomerPage customerPage = new CustomerPage(driver);

    @And("el usuario ingresa al modulo customers")
    public void ingresaModulo() {
        customerPage.clickModuleCustomer();
    }

    @And("el usuario abre el formulario de nuevo cliente")
    public void abrirFormularioNuevoCliente() {
        customerPage.openNewCustomerForm();
    }

    @And("el usuario deja los campos obligatorios vacios y presiona submit")
    public void dejaCamposVacios() {
        customerPage.submitForm();
    }

    @And("el usuario ingresa su nombre {string}, apellido {string} y presiona submit")
    public void registroCliente(String nombre, String apellido){
        customerPage.registerCustomer(nombre, apellido);
        customerPage.submitForm();
    }

    @And("selecciona al cliente con nombre {string} y apellido {string}")
    public void seleccionarCliente(String nombre, String apellido) {
        customerPage.seleccionarCliente(nombre, apellido);
    }

    @And("presiona el botón de eliminar cliente")
    public void presionarEliminar() {
        customerPage.clickEliminarCliente();
    }

    @And("acepta el mensaje de confirmación")
    public void aceptarAlerta() {
        driver.switchTo().alert().accept();
    }


    @Then("se muestran errores por campos obligatorios")
    public void verificaErrores() {
        boolean firstNameError = customerPage.isFirstNameErrorVisible();
        boolean lastNameError = customerPage.isLastNameErrorVisible();

        System.out.println("Error visible para First Name: " + firstNameError);
        System.out.println("Error visible para Last Name: " + lastNameError);
        assertTrue("No aparece error por First Name", firstNameError);
        assertTrue("No aparece error por Last Name", lastNameError);

        System.out.println("Validación de errores obligatorios completada con éxito.");
    }



    @Then("se muestra mensaje de exito tras registro")
    public void registroExitoso(){
        assertTrue(customerPage.isCustomerRegistered());
    }
    @Then("el cliente con nombre {string} y apellido {string} ya no está en la lista")
    public void verificarClienteEliminado(String nombre, String apellido) {

        assertTrue("El cliente aún está en la lista", customerPage.clienteFueEliminado(nombre, apellido));
    }


}

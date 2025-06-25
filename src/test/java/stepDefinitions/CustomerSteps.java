package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CustomerPage;

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


    @Then("se muestran errores por campos obligatorios")
    public void verificaErrores() {
        Assert.assertTrue("No aparece error por First Name", customerPage.isFirstNameErrorVisible());
        Assert.assertTrue("No aparece error por Last Name", customerPage.isLastNameErrorVisible());
    }

    @Then("se muestra mensaje de exito tras registro")
    public void registroExitoso(){
        customerPage.isCustomerRegistered();
    }
}

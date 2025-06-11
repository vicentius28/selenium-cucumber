package stepDefinitions;

import hooks.Hooks;
import pages.BoletasVigentePage;
import io.cucumber.java.en.And;

public class LoginDefinition {

    BoletasVigentePage boletasVigentePage;

    @And("el usuario busca boletas")
    public void elUsuarioBuscaBoletas() {
        boletasVigentePage = new BoletasVigentePage(Hooks.getDriver());
        boletasVigentePage.clickBtnTextoBusquedaConsulta();
        String mensaje = boletasVigentePage.isVisibleLabelElemento();
        System.out.println("Mensaje visible: " + mensaje);
    }
}

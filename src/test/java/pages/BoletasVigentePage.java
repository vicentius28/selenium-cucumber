package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoletasVigentePage extends WebBasePage {

    // Constructor corregido
    public BoletasVigentePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // Inicializa los @FindBy
    }

    // Elementos con @FindBy
    @FindBy(xpath = "//*[contains(text(),'Debe seleccionar un concepto')]")
    private WebElement labelElemento;

    @FindBy(xpath = "(//div[contains(@class, 'clickable')])[1]")
    private WebElement btnTextoBusquedaConsulta;

    // Métodos de interacción
    public void clickBtnTextoBusquedaConsulta() {
        waitUntilElementIsVisibleNonThrow(btnTextoBusquedaConsulta, 50);
        btnTextoBusquedaConsulta.click();
    }

    public String isVisibleLabelElemento() {
        waitUntilElementIsVisibleNonThrow(labelElemento, 30);
        return labelElemento.getText();
    }
}

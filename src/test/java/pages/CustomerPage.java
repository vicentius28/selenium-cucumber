package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;
import java.time.Duration;
import java.util.List;

public class CustomerPage {
    private final WebDriver driver;

    private final By customersModuleLink = By.xpath("//a[normalize-space()='Customers']");
    private final By newCustomerButton = By.cssSelector("button[title='New Customer']");
    private final By firstNameInput = By.xpath("//input[@id='first_name']");
    private final By lastNameInput = By.xpath("//input[@id='last_name']");
    private final By saveCustomerButton = By.xpath("//button[@id='submit']");
    private final By errorFirstName = By.cssSelector("label#first_name-error.has-error");
    private final By errorLastName = By.cssSelector("label#last_name-error.has-error");
    private final By deleteCustomerButton = By.xpath("//button[@id='delete']");
    private final By searchInput = By.xpath("//*[@id='table_holder']/div[1]/div[1]/div[3]/input");

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openNewCustomerForm() {
        driver.findElement(newCustomerButton).click();
    }

    public void clickModuleCustomer() {
        driver.findElement(customersModuleLink).click();
    }

    public void registerCustomer(String name, String lastName) {
        driver.findElement(firstNameInput).sendKeys(name);
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void submitForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#customer_basic_info fieldset")
        ));
        driver.findElement(saveCustomerButton).click();
        Utils.delay(4000);
    }

    public boolean isFirstNameErrorVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorFirstName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public boolean isLastNameErrorVisible() {
        return !driver.findElements(errorLastName).isEmpty();
    }

    public boolean isCustomerRegistered() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("span[data-notify='message']")))
                .isDisplayed();
    }

    public void selectCustomer(String nombre, String apellido) {
        String xpath = String.format("//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]//input[@type='checkbox']", apellido, nombre);
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        checkbox.click();
    }

    public void clickDeleteCustomer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteCustomerButton));
        deleteButton.click();
    }


    public boolean isCustomerDeleted(String nombre, String apellido) {
        String xpath = String.format("//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]", nombre, apellido);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        try {
            System.out.println("Esperando que el cliente ya no esté visible...");
            boolean desaparecio = wait.until(driver -> driver.findElements(By.xpath(xpath)).isEmpty());
            System.out.println("¿Cliente eliminado?: " + desaparecio);
            return desaparecio;
        } catch (Exception e) {
            System.out.println("Excepción al verificar si cliente fue eliminado: " + e.getMessage());
            return false;
        }
    }

    public boolean searchClient(String nombre, String apellido){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {

            System.out.println("Esperando que la caja de texto de busqueda sea visible");
            WebElement searchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            searchElement.click();
            searchElement.sendKeys(nombre+" "+apellido);
            searchElement.sendKeys(Keys.RETURN);
            Utils.delay(500);//Por seguridad, espera respuesta JS
            wait.until(ExpectedConditions.invisibilityOfElementLocated((
                    By.xpath("//*[@id='table_holder']/div[1]/div[2]/div[2]/div[1]/span/span[1]")//wrapper de alerta de espera de busqueda
            )));

            //Espera a que cargue la busqueda y Se verifica contenido en la tabla de clientes
            System.out.println("Se verifica que resultado de busqueda sea unico");

            List<WebElement> rows = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#table tbody tr"), 0));;
            if(rows.isEmpty()) {
                System.out.println("No se encontro cliente");
                return false;
            }

            if(rows.size()> 1){
                System.out.println("Se encontro mas de un cliente");
                return false;
            }
        }
        catch (Exception e){
            System.out.println("Excepción al buscar cliente: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean verificaBusqueda(String nombre, String apellido){

        System.out.println("Se verifican datos encontrados");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        List<WebElement> rows = driver.findElements(By.cssSelector("#table tbody tr"));
        WebElement row = rows.get(0);
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(row, By.tagName("td")));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        System.out.println("Verificacion de nombre y apellido");
        String cellLastName = cells.get(2).getText().trim();
        String cellFirstName = cells.get(3).getText().trim();
        if(!cellFirstName.equalsIgnoreCase(nombre)){
            System.out.println("Nombre de cliente encontrado no coincide");
            return false;
        }

        if (!cellLastName.equalsIgnoreCase(apellido)){
            System.out.println("Apellido de cliente encontrado no coincide");
            return false;
        }

        return true;
    }

}

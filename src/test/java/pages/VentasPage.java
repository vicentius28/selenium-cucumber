package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class VentasPage {
    private final String username = "admin", password  = "pointofsale";
    WebDriver driver;

    public VentasPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(){
        driver.get("http://localhost/ospos/public/login");
        driver.findElement(By.id("input-username")).sendKeys(username);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.name("login-button")).click();
    }

    public boolean loadVentas(){
        if(!driver.findElement(By.id("home_module_list")).isDisplayed())
        {
            return false;
        }
        driver.findElement(By.xpath("//*[@id='home_module_list']/div[7]")).click();

        return true;
    }


}

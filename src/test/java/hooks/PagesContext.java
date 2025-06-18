package hooks;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.VentasPage;
import pages.HeaderPage;

public class PagesContext {
    private static LoginPage loginPage;
    private static VentasPage ventasPage;
    private static HeaderPage headerPage;

    public static void init(WebDriver driver) {
        loginPage = new LoginPage(driver);
        ventasPage = new VentasPage(driver);
        headerPage = new HeaderPage(driver);
    }

    public static LoginPage login() {
        return loginPage;
    }

    public static VentasPage ventas() {
        return ventasPage;
    }

    public static HeaderPage header() {
        return headerPage;
    }
}

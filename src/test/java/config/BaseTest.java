package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.CatalogPage;
import pages.LoginPage;
import pages.OrderPage;
import properties.PropertiesProvider;

import java.io.File;

/**
 * Created by Мужик on 01.02.2018.
 */
public class BaseTest {

    protected LoginPage loginPage;
    protected WebDriver driver;
    protected OrderPage orderPage;
    private PropertiesProvider properties = new PropertiesProvider();
    private String linkBasePage = properties.getMainPageURL();
    private String linkAdminPage = properties.getAdminURL();

    public String linkBasePage() {
        return linkBasePage;
    }




    @BeforeClass
    public void initializeObject() {
        driver = getWebDriver();
        loginPage = new LoginPage(driver);
        orderPage = new OrderPage(driver);
        driver.manage().window().maximize();
        driver.get(linkAdminPage);
    }

    @AfterClass
    public void quitWebDriver() {
        driver.quit();
    }

    @BeforeClass
    private WebDriver getWebDriver() {

        switch (properties.getBrowser()) {
            case "chrome":
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
                default:
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        new File(BaseTest.class.getResource("/geckodriver.exe").getFile()).getPath());
                return new FirefoxDriver();
        }
    }
}

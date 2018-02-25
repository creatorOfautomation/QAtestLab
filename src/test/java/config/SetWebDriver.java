package config;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import properties.PropertiesProvider;

import java.io.File;

public class SetWebDriver {


    private String browser = PropertiesCollection.BROWSER;
    private Logger log = Logger.getLogger(SetWebDriver.class);


    public WebDriver getWebDriver() {

        log.debug("Try to set WebDriver");
        log.debug("The desire browser is: "+ browser);
        switch (browser) {
            case "chrome":
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(SetWebDriver.class.getResource("/chromedriver.exe").getFile()).getPath());
                log.debug("Set WebDriver chrome");
                return new ChromeDriver();
            default:
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        new File(SetWebDriver.class.getResource("/geckodriver.exe").getFile()).getPath());
                log.debug("Set WebDriver firefox");
                return new FirefoxDriver();
        }
    }
}

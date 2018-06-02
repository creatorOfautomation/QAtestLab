package config;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import menuitems.LoginPage;
import menuitems.orderitem.OrderPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    protected LoginPage loginPage;
    protected WebDriver driver;
    private SetWebDriver setWebDriver = new SetWebDriver();
    private Logger log = Logger.getLogger(BaseTest.class);

    protected OrderPage orderPage;
    private String linkBasePage = PropertiesCollection.MAIN_PAGE_LINK;
    private String linkAdminPage = PropertiesCollection.ADMIN_PAGE_LINK;


    public String linkBasePage() {
        return linkBasePage;
    }

    @BeforeMethod

    public void initializeObject() {
        log.info("Starting before method ");
        log.info("Initialization WebDriver");
        driver = setWebDriver.getWebDriver();
        loginPage = new LoginPage(driver);
        orderPage = new OrderPage(driver);
        driver.manage().window().maximize();
        log.info("Try to get page: " + linkAdminPage);
        driver.get(linkAdminPage);
    }

    @AfterTest
    public void quitWebDriver(ITestContext testContext) {

        log.info("The passed tests are: " + testContext.getPassedTests());
        log.info("The failed tests are: " + testContext.getFailedTests());
        log.info("Start afterMethod");

        driver.quit();
    }

    @AfterMethod
    public void setScreenShop(ITestResult testResult) throws IOException {
        String nameOfScreenshot = getNameOfErrorScreenShot(testResult);
        log.debug("Try to capture error-screenshot");
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots/" + nameOfScreenshot + ".jpg"));
            captureLogFileToAllure();
            captureErrorScreenShotToAllure(testResult);

        }
        log.debug("Successfully captured error-screenshot");
        log.debug("The name of error-screenshot: " + nameOfScreenshot);
    }

    @Attachment(value = "errorScreenShot", type = "image/jpg")
    private byte[] captureErrorScreenShotToAllure(ITestResult testResult) {
        try {
            log.debug("Try to attach error-screenshot to allure-report");
            FileInputStream fin = new FileInputStream("errorScreenshots/" + getNameOfErrorScreenShot(testResult) + ".jpg");
            return IOUtils.toByteArray(fin);
        } catch (Exception e) {
            log.debug("Something went wrong while trying to attach error-screenshot to allure-report");
        }
        return new byte[0];
    }


    @Attachment(value = "log", type = "text/log")
    private byte[] captureLogFileToAllure() {
        try {
            log.debug("Try to attach log-file to allure-report");
            FileInputStream fin = new FileInputStream("log/appLog.jpg");
            return IOUtils.toByteArray(fin);
        } catch (Exception e) {
            log.debug("Something went wrong while trying to attach  log-file to allure-report");
        }
        return new byte[0];
    }

    private String getNameOfErrorScreenShot(ITestResult testResult) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        return ((testResult.getName() + " " + dateFormat.format(currentDate).replace(":", "-")));

    }

}

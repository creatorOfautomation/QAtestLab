package config;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.assertj.core.api.SoftAssertions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class Helpers extends DeclareGlobalVariable implements DescribeHelpers {

    public Helpers(WebDriver driver) {
        super(driver);
    }

    private Logger log = Logger.getLogger(Helpers.class);
    public static final String CFE_LOG_MESSAGE = "Can't find element: ";
    public static final String ST_LOG_MESSAGE = " The stackTrace is here: ";
    private XSSFWorkbook workbook;
    private SoftAssertions softAssertions = new SoftAssertions();

    @Override
    public void softAssertEquals(By element, String expectedValue) {
        softAssertions.assertThat(getText(element).toUpperCase()).isEqualTo(expectedValue.toUpperCase());
    }

    @Override
    public void softAssertContainString(By element, String expectedValue) {
        softAssertions.assertThat(getText(element).toUpperCase()).contains(expectedValue.toUpperCase());
    }

    @Override
    public void assertAll() {
        softAssertions.assertAll();
    }

    @Override
    @Step("Клик по элементу: {element}")
    public void click(By element) {

        log.debug("Trying to click on: " + element);
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.elementToBeClickable(element)).click();


    }

    @Override
    @Step("Клик по элементу: {element}")
    public void click(WebElement element) {

        log.debug("Trying to click on: " + element);
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.elementToBeClickable(element)).click();


    }

    @Override
    @Step("В поле {element} вводится значение {value}")
    public void sendKeys(By element, String value) {

        log.debug("Trying to sendKeys: " + value + " on: " + element);
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(value);


    }

    @Override
    @Step("В поле {element} вводится значение {value}")
    public void sendKeys(WebElement element, String value) {

        log.debug("Trying to sendKeys: " + value + " on: " + element);
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.visibilityOf(element)).sendKeys(value);

    }

    @Override
    public void assertEquals(WebElement element, String expectedValue) {
        String actualResult = getText(element);

        log.debug("Try to assert actualResult: " + actualResult + " of element: " + element + " with expectedResult:  " + expectedValue);
        assertThat(actualResult, containsString(expectedValue));

    }

    @Override
    public void assertEquals(By element, String expectedValue) {
        String actualResult = getText(element);

        log.debug("Try to assert actualResult: " + actualResult + " of element: " + element + " with expectedResult:  " + expectedValue);
        assertThat(actualResult, containsString(expectedValue));


        log("Сравнение фактического  результата " + actualResult + " с ожидаемым " + expectedValue);
    }

    @Override
    public String getText(By element) {
        String actualResult;
        log.debug("Try get text of element: " + element);
        actualResult = getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(element)).getText();
        log.debug("The text of element: " + element + " is: " + actualResult);

        return actualResult;

    }

    @Override
    public String getText(WebElement element) {
        String actualResult;

        log.debug("Try get text of element: " + element);
        actualResult = getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element)).getText();
        log.debug("The text of element: " + element + " is: " + actualResult);
        return actualResult;

    }

    @Override
    @Step
    public void log(String logMessage) {
        //method for allure logging

    }

    @Override
    public WebElement findElement(By element) {


        log.debug("Try to find element: " + element);

        return getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(element));


    }

    @Override
    public List<WebElement> findElements(By locator) {
        return getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    @Override
    public void clickJS(By element) {

        log.debug("Try to click withJS on element: " + element);
        WebElement thisElement = getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(element));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click()", thisElement);
        log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE);
        Assert.fail();

    }

    @Override
    public void waitUntilJSWorking() {
        final JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        (new WebDriverWait(getDriver(), 10)).until((ExpectedCondition<Boolean>) dirver -> (Boolean) executor.executeScript("return jQuery.active == 0"));
        new WebDriverWait(getDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public String randomString(int length) {
        final String data = "1234567890qwertyuiopasdfghjklzxcvbnm";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i <= length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));
        return sb.toString();

    }

    @Override
    public String randomPureString(int length) {
        final String data = "qwertyuiopasdfghjklzxcvbnm";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i <= length; i++)


            sb.append(data.charAt(random.nextInt(data.length())));
        return sb.toString();

    }

    @Override
    public String randomEmail() {
        int length = 10;
        String name = "123456789qwertyuiopasdfghjklzxcvbnmm";
        String firstDomain = "qweryuiopasdfghjklzxcvbnm";
        String secondDomain = "qweryuiopasdfghjklzxcvbnm";

        String email = new String();
        Random random = new Random();

        //генерация первой части имейл
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i <= length; i++)
            sb.append(name.charAt(random.nextInt(name.length())));
        email += sb;

        //генерация второй части имейл
        sb = new StringBuilder(length);
        for (int i = 0; i <= length; i++)
            sb.append(firstDomain.charAt(random.nextInt(firstDomain.length())));
        email += "@" + sb;

        //генерация третей части имейл
        sb = new StringBuilder(length);
        for (int i = 0; i <= length; i++)
            sb.append(secondDomain.charAt(random.nextInt(secondDomain.length())));
        email += "." + sb;

        return email;
    }

    @Override
    public String randomNumber(int length) {
        final String data = "1234567890";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i <= length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));
        return sb.toString();
    }

    @Override
    public String randomStringWithCharacters(int length) {
        final String data = "123456789 qwertyuiopasdfghjklzxcvbnmm/*-!@#$%^&*())_+=♥☻☺♦♣♠○8•";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)

            sb.append(data.charAt(random.nextInt(data.length())));

        return sb.toString();
    }

    @Override
    public void getPage(String pageLink) {
        getDriver().get(pageLink);
    }

    @Override
    public String getTestName() {
        StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
        return stackTraceElements[1].toString();

    }

    @Override
    public void clearField(By element) {
        click(element);
        findElement(element).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
    }

    @Override
    public String[] readFromExcel(final String fileName, final int cell) {
        FileInputStream excelFile = null;
        try {

            log.debug("Try to load ExcelFile for read data");
            excelFile = new FileInputStream(PropertiesCollection.REGISTRATION_EXCEL_DATA_PATH + fileName + ".xlsx");
            if (excelFile != null) {
                log.debug("Excel file successfully loaded");
            }
        } catch (FileNotFoundException e) {
            log.error("ERROR to load Excel file! File not found!!! " + e.getMessage());
            e.printStackTrace();
        }
        try {

            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            log.error("ERROR! IOException occured!!!" + e.getMessage());
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        String[] data = new String[rows];
        try {

            for (int i = 0; i < rows; i++) {
                data[i] = sheet.getRow(i).getCell(cell).getStringCellValue();
            }
        } catch (NullPointerException e) {
            log.error("ERROR! Got NullPointerException. There are no data in row of Excel file");
        }
        return data;

    }

}

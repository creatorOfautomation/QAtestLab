package config;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class Helpers extends DeclareGlobalVariable implements DescribeHelpers {

    public Helpers(WebDriver driver) {
        super(driver);
    }

    private Logger log = Logger.getLogger(Helpers.class);
    public static final String CFE_LOG_MESSAGE = "Can't find element: ";
    public static final String ST_LOG_MESSAGE = " The stackTrace is here: ";

    @Override
    @Step("Клик по элементу: {element}")
    public void click(By element) {
        try {
            log.debug("Trying to click on: " + element);
            getWait().ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE + te);
        } catch (Exception e) {
            log.error("Something else went wrong while trying to click on: " + element + ST_LOG_MESSAGE + e);
        }
    }

    @Override
    @Step("Клик по элементу: {element}")
    public void click(WebElement element) {
        try {
            log.debug("Trying to click on: " + element);
            getWait().ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE + te);
        } catch (Exception e) {
            log.error("Something else went wrong while trying to click on: " + element + ST_LOG_MESSAGE + e);
        }
    }

    @Override
    @Step("В поле {element} вводится значение {value}")
    public void sendKeys(By element, String value) {
        try {
            log.debug("Trying to sendKeys: " + value + " on: " + element);
            getWait().ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(value);
        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE + te);
        } catch (Exception e) {
            log.error("Something else went wrong while trying to sendKeys on: " + element + ST_LOG_MESSAGE + e);
        }

    }

    @Override
    @Step("В поле {element} вводится значение {value}")
    public void sendKeys(WebElement element, String value) {
        try {
            log.debug("Trying to sendKeys: " + value + " on: " + element);
            getWait().ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE + te);
        } catch (Exception e) {
            log.error("Something else went wrong while trying to sendKeys on: " + element + " Here is stackTrace: " + e);
        }
    }

    @Override
    public void assertEquals(WebElement element, String expectedValue) {
        String actualResult = getText(element);
        try {
            log.debug("Try to assert actualResult: " + actualResult + " of element: " + element + " with expectedResult:  " + expectedValue);
            assertThat(actualResult, containsString(expectedValue));

        } catch (Exception e) {
            log.error("Something went wrong while tying to assert actual result: "+ actualResult + " with expected result " + expectedValue
                    + ST_LOG_MESSAGE + e);
        }
        log("Сравнение фактического  результата " + actualResult + " с ожидаемым " + expectedValue);
    }

    @Override
    public void assertEquals(By element, String expectedValue) {
        String actualResult = getText(element);
        try {
            log.debug("Try to assert actualResult: " + actualResult + " of element: " + element + " with expectedResult:  " + expectedValue);
            assertThat(actualResult, containsString(expectedValue));

        } catch (Exception e) {
            log.error("Something went wrong while tying to assert actual result: "+ actualResult + " with expected result " + expectedValue
                    + ST_LOG_MESSAGE + e);
        }
        log("Сравнение фактического  результата " + actualResult + " с ожидаемым " + expectedValue);
    }

    @Override
    public String getText(By element) {
        String actualResult;
        try {

            log.debug("Try get text of element: " + element);
            actualResult = getWait().ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(element)).getText();
            log.debug("The text of element: " + element + " is: " + actualResult);
            return actualResult;
        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE + te);
            return null;
        } catch (Exception e) {
            log.error("Something else went wrong while trying to getText on: " + element + ST_LOG_MESSAGE + e);
            return null;
        }
    }

    @Override
    public String getText(WebElement element) {
        String actualResult;
        try {

            log.debug("Try get text of element: " + element);
            actualResult = getWait().ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element)).getText();
            log.debug("The text of element: " + element + " is: " + actualResult);
            return actualResult;
        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE + te);
            return null;
        } catch (Exception e) {
            log.error("Something else went wrong while trying to getText on: " + element + ST_LOG_MESSAGE + e);
            return null;
        }
    }

    @Override
    @Step
    public void log(String logMessage) {
        //method for allure logging

    }

    @Override
    public WebElement findElement(By element) {

        try {
            log.debug("Try to find element: " + element);
            return getWait().ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element+ ST_LOG_MESSAGE +te);
            return null;
        } catch (Exception e) {
            log.error("Something else went wrong while trying to find element: " + element + ST_LOG_MESSAGE + e);
            return null;
        }

    }

    @Override
    public void clickJS(By element) {

        try {
            log.debug("Try to click withJS on element: " + element);
            WebElement thisElement = getWait().ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(element));

            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click()", thisElement);

        } catch (TimeoutException te) {
            log.error(CFE_LOG_MESSAGE + element + ST_LOG_MESSAGE + te);
        } catch (Exception e) {
            log.error("Something else went wrong while trying to clickJS on element: " + element + ST_LOG_MESSAGE + e);
        }

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
}

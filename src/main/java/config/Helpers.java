package config;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class Helpers extends DeclareGlobalVariable implements DescribeHelpers {

    public Helpers(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Клик по элементу: {element}")
    public void click(By element) {
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Override
    @Step("Клик по элементу: {element}")
    public void click(WebElement element) {
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Override
    @Step("В поле {element} вводится значение {value}")
    public void sendKeys(By element, String value) {
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(value);
    }

    @Override
    @Step("В поле {element} вводится значение {value}")
    public void sendKeys(WebElement element, String value) {
        getWait().ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
    }

    @Override
    public void assertEquals(WebElement element, String expectedValue) {

        String actualResult = getText(element);

        assertThat(actualResult, containsString(expectedValue));
        log("Сравнение фактического  результата " + actualResult+ " с ожидаемым " + expectedValue);
    }

    @Override
    public void assertEquals(By element, String expectedValue) {
        String actualResult = getText(element);

        assertThat(actualResult, containsString(expectedValue));
        log("Сравнение фактического  результата " + actualResult+ " с ожидаемым " + expectedValue);
    }

    @Override
    public String getText(By element) {

        return getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(element)).getText();
    }

    @Override
    public String getText(WebElement element) {
        String actualResult = getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element)).getText();
        return actualResult;
    }

    @Override
    @Step
    public void log(String logMessage) {

    }

    @Override
    public WebElement findElement(By locator) {
        return getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public void clickJS(By element) {
        WebElement thisElement = getWait().ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(element));

        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click()", thisElement);
    }

    @Override
    public void waitUntilJSWorking() {
        final JavascriptExecutor executor = (JavascriptExecutor) getDriver();


        (new WebDriverWait(getDriver(), 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver dirver) {
                return (Boolean) executor.executeScript("return jQuery.active == 0");
            }
        });
        new WebDriverWait(getDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}

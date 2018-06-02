package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface DescribeHelpers {

    public WebElement findElement(By locator);

    public List<WebElement> findElements(By locator);

    public void click(By element);

    public void click(WebElement element);

    public void clickJS(By element);

    public void sendKeys(By element, String keys);

    public void sendKeys(WebElement element, String keys);

    public void assertEquals(WebElement element, String expectedValue);

    public void assertEquals(By element, String expectedValue);

    public void softAssertEquals(By element, String expectedValue);

    public void softAssertContainString(By element, String expectedValue);

    public void assertAll();

    public String getText(By element);

    public String getText(WebElement element);

    public void log(String logMessage);

    public void waitUntilJSWorking();

    public String randomString(int length);

    public String randomPureString(int length);

    public String randomEmail();

    public String randomNumber(int length);

    public String randomStringWithCharacters(int length);

    public void getPage(String pageLink);

    public String getTestName();

    public void clearField(By element);

    public String[] readFromExcel(String fileName, int row);

}

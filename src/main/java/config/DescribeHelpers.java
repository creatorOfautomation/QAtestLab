package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface DescribeHelpers {

    public WebElement findElement(By locator);

    public void click(By element);

    public void click(WebElement element);

    public void clickJS(By element);

    public void sendKeys(By element, String keys);

    public void sendKeys(WebElement element, String keys);

    public void assertEquals(WebElement element, String expectedValue);

    public void assertEquals(By element, String expectedValue);

    public String getText(By element);

    public String getText(WebElement element);

    public void log(String logMessage);

    public void waitUntilJSWorking();




}

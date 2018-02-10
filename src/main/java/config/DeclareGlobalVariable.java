package config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class DeclareGlobalVariable {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public WebDriverWait getWait() {
        return wait;
    }

    public DeclareGlobalVariable(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(getDriver(), 12);
        actions = new Actions(getDriver());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Actions getActions() {
        return actions;
    }
}


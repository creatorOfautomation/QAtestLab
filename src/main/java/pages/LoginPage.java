package pages;

import config.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import properties.PropertiesProvider;

public class LoginPage extends Helpers {

    private PropertiesProvider provider = new PropertiesProvider();
    private String loginAdminPage = provider.getAdminLogin();
    private String passwordAdminPage = provider.getAdminPassword();

    public static final String TITLE_OF_ADMIN_PAGE_TEXT = "Пульт";
    public static final By LOGIN_FIELD = By.xpath("//input[@id=\"email\"]");
    public static final By PASSWORD_FIELD = By.xpath("//input[@id=\"passwd\"]");
    public static final By SUBMIT_BUTTON = By.xpath("//*[@class=\"form-group row-padding-top\"]//button[@name=\"submitLogin\"]");
    public static final By TITLE_OF_ADMIN_PAGE = By.xpath("//h2[@class='page-title']");



    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashBoardPage loginInAdminPage(){

        sendKeys(LOGIN_FIELD, loginAdminPage);
        sendKeys(PASSWORD_FIELD, passwordAdminPage);
        click(SUBMIT_BUTTON);
        assertEquals(TITLE_OF_ADMIN_PAGE, TITLE_OF_ADMIN_PAGE_TEXT);
        return new DashBoardPage(getDriver());
    }


}

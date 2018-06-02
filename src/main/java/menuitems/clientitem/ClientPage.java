package menuitems.clientitem;

import config.Helpers;
import config.PropertiesCollection;
import menuitems.dashboarditem.DashBoardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class ClientPage extends Helpers {

    public static final By LOGIN_IN_BUTTON = By.xpath("//div[@class=\"user-info\"]//*[@class=\"hidden-sm-down\"]");
    public static final By SIGN_UP_BUTTON = By.xpath("//div[@class=\"no-account\"]");
    public static final By GENDER_RADIO_BUTTON = By.xpath("//*[@name=\"id_gender\"][@value='1']");
    public static final By FIRST_NAME_FIELD = By.xpath("//*[@name=\"firstname\"]");
    public static final By LAST_NAME_FIELD = By.xpath("//*[@name=\"lastname\"]");
    public static final By EMAIL_FIElD = By.xpath("//*[@class=\"col-md-6\"]//*[@name=\"email\"]");
    public static final By SAVE_CUSTOMER_BUTTON = By.xpath("//*[@data-link-action=\"save-customer\"]");
    public static final By EMAIL_IN_DB = By.xpath("//*[@class=\"pointer\"][4]");


    private String[] newUserData = readFromExcel("RegistrData",1);
    private String userFirstName = newUserData[0];
    private String userLastName = newUserData[1];
    private String userEmail = randomEmail();

    public ClientPage(WebDriver driver) {
        super(driver);
    }



    public ClientPage registrationNewUser () {

        String[] ExcelData = readFromExcel("RegistrData", 1);
        System.out.println(ExcelData[0]);
        getPage(PropertiesCollection.MAIN_PAGE_LINK);
        click(LOGIN_IN_BUTTON);
        click(SIGN_UP_BUTTON);
        sendKeys(FIRST_NAME_FIELD, userFirstName);
        sendKeys(LAST_NAME_FIELD, userLastName);
        sendKeys(EMAIL_FIElD, userEmail);
        click(SAVE_CUSTOMER_BUTTON);
        getPage(PropertiesCollection.ADMIN_PAGE_LINK);
        click(DashBoardPage.CLIENTS_PAGE_TAB);
        assertEquals(EMAIL_IN_DB, userEmail);

        return this;
    }

}

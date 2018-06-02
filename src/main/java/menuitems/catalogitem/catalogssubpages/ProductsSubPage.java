package menuitems.catalogitem.catalogssubpages;

import config.Helpers;
import config.PropertiesCollection;
import menuitems.orderitem.OrderPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsSubPage extends Helpers {

    public static final By ADD_NEW_PRODUCT_BUTTON = By.xpath("//*[@id=\"page-header-desc-configuration-add\"]");
    public static final By NAME_NEW_PRODUCT_FIELD = By.xpath("//*[@id=\"form_step1_name_1\"]");
    public static final By QUANTITY_OF_NEW_PRODUCT_FIELD = By.xpath("//*[@id=\"form_step3_qty_0\"]");
    public static final By PRICE_OF_NEW_PRODUCT_TUB = By.xpath("//*[@id='tab_step2']//a");
    public static final By PRICE_OF_NEW_PRODUCT_FIELD = By.xpath("//*[@id='form_step2_price']");
    public static final By SAVE_NEW_PRODUCT_BUTTON = By.xpath("//*[@class=\"btn btn-primary js-btn-save\"]");
    public static final By SWITCH_ACTIVATION_BUTTON = By.xpath("//*[@class=\"switch-input \"]");

    private Logger log = Logger.getLogger(ProductsSubPage.class);
    private String nameOfNewProduct = randomPureString(12);
    private String quantityOfProduct = randomNumber(2);
    private String priceOfNewProduct = randomNumber(3);

    private By TITLE_OF_NEW_PRODUCT = By.xpath("//*[contains(text(), '" + nameOfNewProduct + "')]");
    public ProductsSubPage(WebDriver driver) {
        super(driver);
    }

    public ProductsSubPage testAddNewProduct() {

        log.info("Starting the test: " + getTestName());
        click(ADD_NEW_PRODUCT_BUTTON);
        sendKeys(NAME_NEW_PRODUCT_FIELD, nameOfNewProduct);
        sendKeys(QUANTITY_OF_NEW_PRODUCT_FIELD, quantityOfProduct);
        click(PRICE_OF_NEW_PRODUCT_TUB);
        clearField(PRICE_OF_NEW_PRODUCT_FIELD);
        sendKeys(PRICE_OF_NEW_PRODUCT_FIELD, priceOfNewProduct);
        click(SWITCH_ACTIVATION_BUTTON);
        click(SAVE_NEW_PRODUCT_BUTTON);
        getPage(PropertiesCollection.MAIN_PAGE_LINK);
        click(OrderPage.ALL_PRODUCT_LINK);
        click(TITLE_OF_NEW_PRODUCT);


        log.info("The end of test: " + getTestName());
        return this;
    }
}

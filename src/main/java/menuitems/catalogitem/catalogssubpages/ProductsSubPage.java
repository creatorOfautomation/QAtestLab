package menuitems.catalogitem.catalogssubpages;

import config.Helpers;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsSubPage extends Helpers {

    public static final By ADD_NEW_PRODUCT_BUTTON = By.xpath("//*[@id=\"page-header-desc-configuration-add\"]");
    public static final By NAME_NEW_PRODUCT_FIELD = By.xpath("//*[@id=\"form_step1_name_1\"]");
    public static final By QUANTITY_OF_PRODUCT_FIELD = By.xpath("//*[@id=\"form_step3_qty_0\"]");
    public static final By SAVE_NEW_PRODUCT_BUTTON = By.xpath("//*[@class=\"btn btn-primary js-btn-save\"]");

    private Logger log = Logger.getLogger(ProductsSubPage.class);
    private String nameOfNewProduct = randomPureString(12);
    private String quantityOfProduct = randomNumber(2);

    public ProductsSubPage(WebDriver driver) {
        super(driver);
    }

    public ProductsSubPage testAddNewProduct() {

        log.info("Starting the test: " + getTestName());
        click(ADD_NEW_PRODUCT_BUTTON);
        sendKeys(NAME_NEW_PRODUCT_FIELD, nameOfNewProduct);
        sendKeys(QUANTITY_OF_PRODUCT_FIELD, quantityOfProduct);
        click(SAVE_NEW_PRODUCT_BUTTON);
        log.info("The end of test: " + getTestName());
        return this;
    }
}

package pages.subpages;

import config.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import java.util.logging.Logger;

public class ProductsSubPage extends Helpers{

    public static final By ADD_NEW_PRODUCT_BUTTON = By.xpath("//*[@id=\"page-header-desc-configuration-add\"]");
    public static final By NANE_NEW_PRODUCT_FIELD = By.xpath("//*[@id=\"form_step1_name_1\"]");
    public static final By QUANTITY_OF_PRODUCT_FIELD = By.xpath("//*[@id=\"form_step3_qty_0\"]");
    public static final By SAVE_NEW_PRODUCT_BUTTON = By.xpath("//*[@class=\"btn btn-primary js-btn-save\"]");

    private String nameOfNewProduct = "TestProduct";
    private int quantityOfProduct = 10;
    private Logger log = Logger.getLogger(ProductsSubPage.class.getSimpleName());
    



    public ProductsSubPage(WebDriver driver) {
        super(driver);
    }

    public ProductsSubPage testAddNewProduct() {
        StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
       String methodName = stackTraceElements[1].toString();
        log.info("Start" + methodName);
        click(ADD_NEW_PRODUCT_BUTTON);

        return this;
    }
}

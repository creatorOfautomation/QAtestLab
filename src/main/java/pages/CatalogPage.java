package pages;

import config.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.subpages.ProductsSubPage;

public class CatalogPage extends Helpers {

    public static final By PRODUCT_PAGE_SUB_TUB = By.xpath("//*[@id=\"subtab-AdminProducts\"]//a");

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public ProductsSubPage getProductSubPage() {
       /* waitUntilJSWorking();

        getActions().moveToElement(findElement(DashBoardPage.CATALOG_PAGE_TAB))
                .moveToElement(findElement(PRODUCT_PAGE_SUB_TUB))
                .build()
                .perform();*/
        return new ProductsSubPage(getDriver());

    }
}

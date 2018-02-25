package menuitems.catalogitem;

import config.Helpers;
import menuitems.catalogitem.catalogssubpages.CategoriesSubPage;
import menuitems.catalogitem.catalogssubpages.MonitoringSubPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import menuitems.catalogitem.catalogssubpages.ProductsSubPage;

public class CatalogPage extends Helpers {

    public static final By PRODUCT_PAGE_SUB_TUB = By.xpath("//*[@class=\"link-levelone -active\"]//li[1]/a");
    public static final By MONITORING_PAGE_SUB_TUB = By.xpath("//*[@class=\"link-levelone -active\"]//li[3]/a");
    public static final By CATEGORIES_PAGE_SUB_TUB = By.xpath("//*[@class=\"link-levelone -active\"]//li[2]/a");


    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public ProductsSubPage getProductSubPage() {

        clickJS(PRODUCT_PAGE_SUB_TUB);
        return new ProductsSubPage(getDriver());

    }

    public CategoriesSubPage getCategoriesSubPage() {

        clickJS(CATEGORIES_PAGE_SUB_TUB);
        return new CategoriesSubPage(getDriver());
    }

    public MonitoringSubPage getMonitoringSubPage() {

        clickJS(MONITORING_PAGE_SUB_TUB);
        return new MonitoringSubPage(getDriver());
    }

}

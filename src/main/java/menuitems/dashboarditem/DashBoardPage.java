package menuitems.dashboarditem;

import config.Helpers;
import menuitems.clientitem.ClientPage;
import menuitems.orderitem.OrderPage;
import menuitems.statisticitem.StatisticsPage;
import menuitems.supportitem.SupportPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import menuitems.catalogitem.CatalogPage;

public class DashBoardPage extends Helpers {

    public static final By ORDER_PAGE_TAB = By.xpath("//*[@id='subtab-AdminParentOrders']");
    public static final By CATALOG_PAGE_TAB = By.xpath("//*[@id='subtab-AdminCatalog']");
    public static final By CLIENTS_PAGE_TAB = By.xpath("//li[@data-submenu=\"23\"]");
    public static final By SUPPORT_PAGE_TAB = By.xpath("//*[@id='subtab-AdminParentCustomerThreads']");
    public static final By STATISTICS_PAGE_TAB = By.xpath("//*[@id='subtab-AdminStats']");

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }


    public OrderPage getOrderPage() {

        click(ORDER_PAGE_TAB);
        return new OrderPage(getDriver());
    }

    public CatalogPage getCatalogPage() {

        click(CATALOG_PAGE_TAB);
        return new CatalogPage(getDriver());
    }

    public ClientPage getClientPage() {
        click(CLIENTS_PAGE_TAB);
        return new ClientPage(getDriver());
    }

    public SupportPage getSupportPage() {
        click(SUPPORT_PAGE_TAB);
        return new SupportPage(getDriver());
    }

    public StatisticsPage getStatisticsPage() {
        click(STATISTICS_PAGE_TAB);
        return new StatisticsPage(getDriver());
    }
}

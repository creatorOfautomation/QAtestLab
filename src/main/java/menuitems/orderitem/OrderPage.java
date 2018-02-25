package menuitems.orderitem;

import config.Helpers;
import menuitems.dashboarditem.DashBoardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import menuitems.subpages.CartsSubPage;

public class OrderPage extends Helpers {

    public static final By SUB_MENU_ORDER = By.xpath("//*[@id='subtab-AdminOrders']");
    public static final By SUB_MENU_CARTS = By.xpath("//*[@id=\"subtab-AdminCarts\"]");


    public OrderPage(WebDriver driver) {
        super(driver);
    }


    public OrderPage getOrderTab() {

        getActions().moveToElement(findElement(DashBoardPage.ORDER_PAGE_TAB))
                .moveToElement(findElement(SUB_MENU_ORDER))
                .click()
                .build().perform();

        return this;
    }

    public CartsSubPage getCartsSubPage() {

        getActions()
                .moveToElement(findElement(DashBoardPage.ORDER_PAGE_TAB))
                .build()
                .perform();
        getActions()
                .moveToElement(findElement(SUB_MENU_CARTS))
                .click()
                .build()
                .perform();

        return new CartsSubPage(getDriver());
    }

    public OrderPage verifyOrder() {
        //do something

        return this;
    }
}

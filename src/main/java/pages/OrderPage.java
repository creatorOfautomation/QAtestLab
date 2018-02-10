package pages;

import config.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends Helpers {

    public static final By SUB_MENU_ORDER = By.xpath("//*[@id='subtab-AdminOrders']");


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

    public OrderPage verifyOrder() {
        //do something

        return this;
    }
}

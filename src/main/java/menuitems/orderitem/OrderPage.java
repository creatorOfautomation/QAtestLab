package menuitems.orderitem;

import config.Helpers;
import config.PropertiesCollection;
import menuitems.dashboarditem.DashBoardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import menuitems.subpages.CartsSubPage;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class OrderPage extends Helpers {

    public static final By SUB_MENU_ORDER = By.xpath("//*[@id='subtab-AdminOrders']");
    public static final By SUB_MENU_CARTS = By.xpath("//*[@id=\"subtab-AdminCarts\"]");

    private String titleOfAddingToCart = "Товар успішно додано до Вашого кошика";
    public static final By ALL_PRODUCT_LINK = By.xpath("//*[@class=\"all-product-link pull-xs-left pull-md-right h4\"]");
    public static final By TITLE_OF_ALL_PRODUCTS = By.xpath("//*[@class=\"h3 product-title\"]");
    public static final By ADD_TO_CART_BUTTON = By.xpath("//*[@data-button-action=\"add-to-cart\"]");
    public static final By TITLE_SUCCESFULL_ADDING_TO_CART= By.xpath("//*[@id='myModalLabel']");




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

    public OrderPage testMakeOrder() {

        int randomProduct;
        getPage(PropertiesCollection.MAIN_PAGE_LINK);
        click(ALL_PRODUCT_LINK);
        ArrayList<WebElement> el = new ArrayList<>();
        el.addAll(findElements(TITLE_OF_ALL_PRODUCTS));
        randomProduct = (int) Math.random() * el.size();
        click(el.get(randomProduct));
        click(ADD_TO_CART_BUTTON);
        assertEquals(TITLE_SUCCESFULL_ADDING_TO_CART, titleOfAddingToCart);

        return this;
    }
}

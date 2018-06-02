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
    public static final By PRICE_OF_PRODUCT_IN_CART = By.xpath("//*[@class='modal-content']//*[@class=\"col-md-6\"][2]//p[1]");
    public static final By NAME_OF_PRODUCT_IN_CART = By.xpath("//*[@class='h6 product-name']");
    public static final By ADD_TO_CART_BUTTON = By.xpath("//*[@data-button-action=\"add-to-cart\"]");
    public static final By VIEW_PRODUCT_BUTTON_IN_ADMIN_PAGE = By.xpath("//*[@class=\"btn-group pull-right\"]");
    public static final By TITLE_SUCCESSFUL_ADDING_TO_CART = By.xpath("//*[@id='myModalLabel']");
    public static final By PRODUCT_DESCRIPTION_IN_ADMIN_CART = By.xpath("//*[@id=\"orderProducts\"]//a");
    public static final By PRODUCT_PRICE_IN_ADMIN_CART = By.xpath("//*[@id=\"orderProducts\"]//tr[1]//td[@class=\"text-right\"][1]");

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

        int randomNumberOfProduct;
        String nameOfProductInCart;
        String priceOfProductInCart;
        ArrayList<WebElement> el = new ArrayList<>();
        getPage(PropertiesCollection.MAIN_PAGE_LINK);
        click(ALL_PRODUCT_LINK);
        el.addAll(findElements(TITLE_OF_ALL_PRODUCTS));
        randomNumberOfProduct = ((int) Math.random() * el.size());
        click(el.get(randomNumberOfProduct));
        el.clear();
        click(ADD_TO_CART_BUTTON);
        softAssertEquals(TITLE_SUCCESSFUL_ADDING_TO_CART, titleOfAddingToCart);
        nameOfProductInCart = getText(NAME_OF_PRODUCT_IN_CART);
        priceOfProductInCart = getText(PRICE_OF_PRODUCT_IN_CART);
        getPage(PropertiesCollection.ADMIN_PAGE_LINK);
        getCartsSubPage();
        el.addAll(findElements(VIEW_PRODUCT_BUTTON_IN_ADMIN_PAGE));
        click(el.get(0));
        el.clear();
        softAssertContainString(PRODUCT_DESCRIPTION_IN_ADMIN_CART, nameOfProductInCart);
        softAssertEquals(PRODUCT_PRICE_IN_ADMIN_CART, priceOfProductInCart);
        assertAll();



        return this;
    }
}

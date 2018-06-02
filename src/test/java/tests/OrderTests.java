package tests;

import config.BaseTest;
import org.testng.annotations.Test;

public class OrderTests extends BaseTest {


    @Test
    public void testMakeOrder () {
        loginPage.loginInAdminPage()
                .getOrderPage()
                .testMakeOrder();
    }
}

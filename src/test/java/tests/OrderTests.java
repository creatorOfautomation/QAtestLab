package tests;

import config.BaseTest;
import org.testng.annotations.Test;

public class OrderTests extends BaseTest {

    @Test
    public void test() {

        loginPage.loginInAdminPage()
                .getOrderPage()
                .getOrderTab()
                .verifyOrder();
    }

}

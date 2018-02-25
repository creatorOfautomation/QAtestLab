package tests;

import config.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CatalogTests extends BaseTest {

    @Test
    public void test() {
        loginPage.loginInAdminPage()
                .getCatalogPage()
                .getProductSubPage()
                .testAddNewProduct();
    }

}

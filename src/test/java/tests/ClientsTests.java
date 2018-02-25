package tests;

import config.BaseTest;
import org.testng.annotations.Test;

public class ClientsTests extends BaseTest {

    @Test
    protected void testRegistrationUser() {
        loginPage.loginInAdminPage()
                .getClientPage()
                .registrationNewUser();
    }
}

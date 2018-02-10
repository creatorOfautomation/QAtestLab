package properties;

import java.io.IOException;
import java.util.Properties;

public class PropertiesProvider {

    private Properties properties;

    public PropertiesProvider() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.exit(1);
        }
    }

    public String getMainPageURL() {
        return properties.getProperty("base.link.page.url");
    }

    public String getAdminURL() {
        return properties.getProperty("base.admin.link");
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getAdminLogin() {
        return properties.getProperty("login.admin.page");
    }

    public String getAdminPassword() {
        return properties.getProperty("password.admin.page");
    }

}

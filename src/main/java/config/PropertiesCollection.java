package config;

import properties.PropertiesProvider;

public class PropertiesCollection {

    private PropertiesCollection() {

    }

    private static final PropertiesProvider provider = new PropertiesProvider();

    public static final String ADMIN_PAGE_LINK = provider.getAdminURL();
    public static final String MAIN_PAGE_LINK = provider.getMainPageURL();
    public static final String BROWSER = provider.getBrowser();
    public static final String ADMIN_LOGIN = provider.getAdminLogin();
    public static final String ADMIN_PASSWORD = provider.getAdminPassword();
    public static final String REGISTRATION_EXCEL_DATA_PATH = provider.getExcelDataPath();
}

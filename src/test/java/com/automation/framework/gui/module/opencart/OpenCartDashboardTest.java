package com.automation.framework.gui.module.opencart;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.site.opencart.Dashboard;
import com.automation.framework.page.site.opencart.Login;
import com.automation.framework.page.site.saucedemo.MenuNavigationPage;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.automation.framework.page.site.saucedemo.MenuOption.USERS;

public class OpenCartDashboardTest extends BaseTestNGTest {

    @Value("${app.opencart.url}")
    private String url;

    @LazyAutowired
    private Login login;

    @LazyAutowired
    private Dashboard dashboard;

    @LazyAutowired
    private MenuNavigationPage menuNavigation;

    @BeforeClass
    public void setup() {
        this.login.goToURL(url)
                .enterUsernameAndPassword()
                .clickLoginButton();
    }

    @Test(priority = 0)
    public void verifyDashboard() {
        this.dashboard.selectCountryByCode("in");
    }

    @Test(priority = 1, enabled = false)
    public void verify() {
        this.menuNavigation.navigateToMenu(USERS);
    }

}
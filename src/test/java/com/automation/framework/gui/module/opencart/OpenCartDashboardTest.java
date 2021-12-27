package com.automation.framework.gui.module.opencart;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.hrm.MenuNavigationPage;
import com.automation.framework.page.app.opencart.Dashboard;
import com.automation.framework.page.app.opencart.Login;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.automation.framework.page.app.hrm.MenuOption.USERS;


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
        this.login.goTo(url);
        this.login.enterUsernameAndPassword();
        this.login.clickLoginButton();
    }

    @Test(priority = 0)
    public void verifyDashboard() {
        this.dashboard.selectCountryByCode("in");
    }

    @Test(priority = 1, enabled = false)
    public void verify() {
        menuNavigation.navigateToMenu(USERS);
    }

}